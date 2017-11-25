package net.brian.coding.db.redis.cluster;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Client;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

public class JedisClusterDemo {

    JedisCluster jedisCluster = null;

    static String prefix = "luffi:lbl";
    static String KEY_SPLIT = ":"; //���ڸ�������ǰ׺�뻺���ֵ

    String nameKey = prefix + KEY_SPLIT + "name";

    /**
     * ��Ϊ�ǲ��ԣ�����û��д����
     */
    @Before
    public void before(){
        String[] serverArray = "redis��Ⱥ��ַ".split(",");
        Set<HostAndPort> nodes = new HashSet<>();

        for (String ipPort : serverArray) {
            String[] ipPortPair = ipPort.split(":");
            nodes.add(new HostAndPort(ipPortPair[0].trim(), Integer.valueOf(ipPortPair[1].trim())));
        }

        //ע�⣺���ﳬʱʱ�䲻Ҫ̫�̣������г�ʱ���Ի��ơ�����������httpclient��dubbo��RPC���ҲҪע�����
        jedisCluster = new JedisCluster(nodes, 1000, 1000, 1, "redis��Ⱥ����", new GenericObjectPoolConfig());

//        ��������Զ���ʹ�á�nameKey�����Եģ�����������֮ǰ�Ȱ����keyɾ��
        jedisCluster.del(nameKey);
    }

    /**
     * ����
     */
    @Test
    public void publish(){
        System.out.println(jedisCluster.publish("channel1", "ss"));
    }
    /**
     * ����
     */
    @Test
    public void psubscribe(){
//        jedisCluster.psubscribe(new JedisPubSubListener(), "channel1");//��ͨ���
//        jedisCluster.subscribe(new JedisPubSubListener(), "channel1");
    }

    /**
     * ���ַ�����д
     */
    @Test
    public void setStringData(){
        System.out.println(jedisCluster.set(nameKey, "����"));
        System.out.println(jedisCluster.get(nameKey));
    }

    /**
     * setnx : ���key���ڣ�����0����������ڣ������óɹ���
     * setnx����˼��set if not exist.
     */
    @Test
    public void setnxTest(){
        System.out.println(jedisCluster.setnx(nameKey, "����"));//key�����ڣ�����ֵΪ1
        System.out.println(jedisCluster.get(nameKey));

        System.out.println(jedisCluster.setnx(nameKey, "����"));//�Ѿ����ڣ�����ֵΪ0
        System.out.println(jedisCluster.get(nameKey));
    }

    /**
     * ���ַ�����д,������ʱ��
     */
    @Test
    public void setexTest() throws InterruptedException {
        System.out.println(jedisCluster.setex(nameKey, 3, "����"));//ʱ�䵥λ����
        for(int i = 0 ; i < 5 ; i ++){
            System.out.println(jedisCluster.get(nameKey));//�����Ժ�redis��Ⱥ�Զ�ɾ��
            Thread.sleep(1000);
        }
    }


    /**
     * �������ַ���
     */
    @Test
    public void setrangeTest() throws InterruptedException {
        System.out.println(jedisCluster.set(nameKey, "852026881@qq.com"));
        System.out.println(jedisCluster.get(nameKey));//�����852026881@qq.com

        //��offset=8��ʼ�滻�ַ���value��ֵ
        System.out.println(jedisCluster.setrange(nameKey, 8, "abc"));//�����85202688abcq.com
        System.out.println(jedisCluster.get(nameKey));

        System.out.println(jedisCluster.setrange(nameKey, 8, "abcdefghhhhhh"));//�����85202688abcdefghhhhhh
        System.out.println(jedisCluster.get(nameKey));

        //��ѯ�Ӵ�,����startOffset��endOffset���ַ�
        System.out.println(jedisCluster.getrange(nameKey, 2, 5));//�����2026
    }


    /**
     * ��������key
     * keySlot�㷨�У����key����{}���ͻ�ʹ�õ�һ��{}�ڲ����ַ�����Ϊhash key�������Ϳ��Ա�֤ӵ��ͬ��{}�ڲ��ַ�����key�ͻ�ӵ����ͬslot��
     * �ο�  http://brandnewuser.iteye.com/blog/2314280
     * redis.clients.util.JedisClusterCRC16#getSlot(java.lang.String)
     *
     * ע�⣺�����Ļ�����������hash����ͬ��slot�е����ݶ��ŵ���ͬһ��slot�У�����ʹ�õ�ʱ��Ҫע�����ݲ�Ҫ̫�ർ��һ��slot�������������ݷֲ������ȣ�
     *
     * MSET ��һ��ԭ����(atomic)���������и��� key ������ͬһʱ���ڱ����ã�ĳЩ���� key �����¶���һЩ���� key û�иı������������ܷ�����
     */
    @Test
    public void msetTest() throws InterruptedException {
        /**
         * jedisCluster.mset("sf","d","aadf","as");
         * ֱ������д���ᱨ��redis.clients.jedis.exceptions.JedisClusterException: No way to dispatch this command to Redis Cluster because keys have different slots.
         * ������Ϊkey����ͬһ��slot��
         */

        String result = jedisCluster.mset("{" + prefix + KEY_SPLIT + "}" + "name", "����", "{" + prefix + KEY_SPLIT + "}" + "age", "23", "{" + prefix + KEY_SPLIT + "}" + "address", "adfsa", "{" + prefix + KEY_SPLIT + "}" + "score", "100");
        System.out.println(result);

        String name = jedisCluster.get("{" + prefix + KEY_SPLIT + "}" + "name");
        System.out.println(name);

        Long del = jedisCluster.del("{" + prefix + KEY_SPLIT + "}" + "age");
        System.out.println(del);

        List<String> values = jedisCluster.mget("{" + prefix + KEY_SPLIT + "}" + "name", "{" + prefix + KEY_SPLIT + "}" + "age", "{" + prefix + KEY_SPLIT + "}" + "address");
        System.out.println(values);
    }


    /**
     *  MSETNX �����ֻ�������и��� key �������ڵ�����½������ò�����
     *  http://doc.redisfans.com/string/mset.html
     */
    @Test
    public void msetnxTest() throws InterruptedException {
        Long msetnx = jedisCluster.msetnx(
                "{" + prefix + KEY_SPLIT + "}" + "name", "����",
                "{" + prefix + KEY_SPLIT + "}" + "age", "23",
                "{" + prefix + KEY_SPLIT + "}" + "address", "adfsa",
                "{" + prefix + KEY_SPLIT + "}" + "score", "100");
        System.out.println(msetnx);

        System.out.println(jedisCluster.mget(
                "{" + prefix + KEY_SPLIT + "}" + "name",
                "{" + prefix + KEY_SPLIT + "}" + "age",
                "{" + prefix + KEY_SPLIT + "}" + "address",
                "{" + prefix + KEY_SPLIT + "}" + "score"));//[����, 23, adfsa, 100]

        //name���key�Ѿ����ڣ�����mset��ԭ�ӵģ�����ָ�ȫ��ʧ��
        msetnx = jedisCluster.msetnx(
                "{" + prefix + KEY_SPLIT + "}" + "phone", "110",
                "{" + prefix + KEY_SPLIT + "}" + "name", "����",
                "{" + prefix + KEY_SPLIT + "}" + "abc", "asdfasfdsa");
        System.out.println(msetnx);


        System.out.println(jedisCluster.mget(
                "{" + prefix + KEY_SPLIT + "}" + "name",
                "{" + prefix + KEY_SPLIT + "}" + "age",
                "{" + prefix + KEY_SPLIT + "}" + "address",
                "{" + prefix + KEY_SPLIT + "}" + "score",
                "{" + prefix + KEY_SPLIT + "}" + "phone",
                "{" + prefix + KEY_SPLIT + "}" + "abc"));//[����, 23, adfsa, 100, null, null]
    }


    /**
     *  getset:����keyֵ�������ؾ�ֵ
     */
    @Test
    public void getsetTest() throws InterruptedException {
        System.out.println(jedisCluster.set(nameKey, "zhangsan"));
        System.out.println(jedisCluster.get(nameKey));
        System.out.println(jedisCluster.getSet(nameKey, "lisi"));
        System.out.println(jedisCluster.get(nameKey));
    }

    /**
     *  append: ׷��. �䷵��ֵ��׷�Ӻ����ݵĳ���
     */
    @Test
    public void appendTest() throws InterruptedException {
        System.out.println(jedisCluster.append(nameKey, "aa"));
        System.out.println(jedisCluster.get(nameKey));
        System.out.println(jedisCluster.append(nameKey, "lisi"));
        System.out.println(jedisCluster.get(nameKey));
    }


    /**
     *  incrf:
     *  �� key �д��������ֵ��һ��

     ��� key �����ڣ���ô key ��ֵ���ȱ���ʼ��Ϊ 0 ��Ȼ����ִ�� INCR ������

     ���ֵ������������ͣ����ַ������͵�ֵ���ܱ�ʾΪ���֣���ô����һ������

     ��������ֵ������ 64 λ(bit)�з������ֱ�ʾ֮�ڡ�

     ����һ������ַ����Ĳ�������Ϊ Redis û��ר�õ��������ͣ����� key �ڴ�����ַ���������Ϊʮ���� 64 λ�з���������ִ�� INCR ������

     ����ֵ��     ִ�� INCR ����֮�� key ��ֵ��

     ���������⣬�������ݽ������10000    �������о� TODO
     ������Ϊ���õĳ�ʱʱ��̫С�ˣ���ȥ�����ˣ��������ս������10000
     */
    @Test
    public void incrTest() throws InterruptedException {
        /**
         * �����̰߳�ȫ
         */
        jedisCluster.del("incrNum");
        final AtomicInteger atomicInteger = new AtomicInteger(0);
        final CountDownLatch countDownLatch = new CountDownLatch(10);
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for(int i = 0 ; i < 10 ; i ++){
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    //ÿ���߳�����1000�Σ�ÿ�μ�1
                    for(int j = 0 ; j < 1000 ; j ++){
                        atomicInteger.incrementAndGet();
                        jedisCluster.incr("incrNum");
                    }

                    countDownLatch.countDown();
                }
            });
        }

        countDownLatch.await();
        System.out.println(jedisCluster.get("incrNum"));
        System.out.println(atomicInteger);
    }



    /**
     *
     * @throws InterruptedException
     */
    @Test
    public void hashTest() throws InterruptedException {
        String hashKey = "hashKey";
        jedisCluster.del(hashKey);

        System.out.println(jedisCluster.hset(hashKey, "program_zhangsan", "111"));
        System.out.println(jedisCluster.hexists(hashKey, "program_zhangsan"));
        System.out.println(jedisCluster.hset(hashKey, "program_zhangsan", "222"));

        System.out.println(jedisCluster.hset(hashKey, "program_wangwu", "333"));
        System.out.println(jedisCluster.hset(hashKey, "program_lisi", "444"));

        System.out.println("hkeys:" + jedisCluster.hkeys(hashKey));

        System.out.println(jedisCluster.hgetAll(hashKey));
        System.out.println(jedisCluster.hincrBy(hashKey, "program_zhangsan", 2));
        System.out.println(jedisCluster.hmget(hashKey, "program_zhangsan", "program_lisi"));

        jedisCluster.hdel(hashKey, "program_wangwu");
        System.out.println(jedisCluster.hgetAll(hashKey));


        System.out.println("hsetnx:" + jedisCluster.hsetnx(hashKey, "program_lisi", "666"));

        System.out.println("hvals:" + jedisCluster.hvals(hashKey));

        System.out.println("expire:" + jedisCluster.expire(hashKey, 3));

        for(int i = 0 ; i < 5 ; i ++){
            System.out.println(jedisCluster.hgetAll(hashKey));
            Thread.sleep(1000);
        }

    }
    /**
     * ģ���Ƚ��ȳ�����
     * ������ ������
     */
    @Test
    public void queue() throws InterruptedException {
        String key = prefix + KEY_SPLIT + "queue";
        jedisCluster.del(key);

        System.out.println(jedisCluster.lpush(key, "1", "2", "3"));
        System.out.println(jedisCluster.lpush(key, "4"));
        System.out.println(jedisCluster.lpush(key, "5"));
        System.out.println("lrange:" + jedisCluster.lrange(key, 0, -1));

        System.out.println("lindex[2]:" + jedisCluster.lindex(key, 2));
        //�ڡ�3����ǰ����롰100��
        System.out.println("linsert:" + jedisCluster.linsert(key, Client.LIST_POSITION.BEFORE, "3", "100"));
        System.out.println("lrange:" + jedisCluster.lrange(key, 0, -1));

        //д��ȥ��˳����12345����ȡ������Ҳ��12345
        for(int i = 0 ; i< 6 ; i ++){
            System.out.println(jedisCluster.rpop(key));
        }

//        �����ﵽ����������������ģʽ��Ҫʹ������ʽ���вſɡ��������д����ͻ��˲��ԡ�
    }



    /**
     * Set����
     */
    @Test
    public void setTest() throws InterruptedException {
        String keyA = "{" + prefix + KEY_SPLIT + "set}a";
        String keyB = "{" + prefix + KEY_SPLIT + "set}b";
        jedisCluster.del(keyA);
        jedisCluster.del(keyB);

        System.out.println(jedisCluster.sadd(keyA, "a", "b", "c"));//�������������
        System.out.println(jedisCluster.sadd(keyA, "a"));//�������������.�����ǲ������ظ���
        System.out.println(jedisCluster.sadd(keyA, "d"));//�������������
        System.out.println(jedisCluster.smembers(keyA));//���ؼ�����������
        System.out.println(jedisCluster.scard(keyA));//���ؼ��ϳ���
        System.out.println("c�Ƿ��ڼ���A�У�" + jedisCluster.sismember(keyA, "c"));//�ж� member Ԫ���Ƿ񼯺� key �ĳ�Ա��
        /*
        �� Redis 2.6 �汾��ʼ�� SRANDMEMBER ������ܿ�ѡ�� count ������
��� count Ϊ��������С�ڼ��ϻ�������ô�����һ������ count ��Ԫ�ص����飬�����е�Ԫ�ظ�����ͬ����� count ���ڵ��ڼ��ϻ�������ô�����������ϡ�
��� count Ϊ��������ô�����һ�����飬�����е�Ԫ�ؿ��ܻ��ظ����ֶ�Σ�������ĳ���Ϊ count �ľ���ֵ��
         */
        System.out.println(jedisCluster.srandmember(keyA));//���ؼ����е�һ�����Ԫ�ء�
        System.out.println(jedisCluster.spop(keyA)); //�Ƴ������ؼ����е�һ�����Ԫ�ء�
        System.out.println(jedisCluster.smembers(keyA));//���ؼ�����������
        System.out.println("---------");

        /*
        SMOVE ��ԭ���Բ�����
��� source ���ϲ����ڻ򲻰���ָ���� member Ԫ�أ��� SMOVE ���ִ���κβ����������� 0 ������ member Ԫ�ش� source �����б��Ƴ�������ӵ� destination ������ȥ��
�� destination �����Ѿ����� member Ԫ��ʱ�� SMOVE ����ֻ�Ǽ򵥵ؽ� source �����е� member Ԫ��ɾ����
�� source �� destination ���Ǽ�������ʱ������һ������
ע����������redis-cluster��ʹ��SMOVE��redis.clients.jedis.exceptions.JedisClusterException: No way to dispatch this command to Redis Cluster because keys have different slots.
����취���Բο������mset���ʹ�á�{}�������������õ�ͬһ��slot��
         */
        System.out.println(jedisCluster.smove(keyA, keyB, "a"));//���ؼ�����������
        System.out.println("keyA: "+jedisCluster.smembers(keyA));//���ؼ�����������
        System.out.println("keyB: "+jedisCluster.smembers(keyB));//���ؼ�����������

        System.out.println(jedisCluster.sadd(keyB, "a", "f", "c"));//�������������
        System.out.println(jedisCluster.sdiff(keyA, keyB));//� keyA-keyB
        System.out.println(jedisCluster.sinter(keyA, keyB));//����
        System.out.println(jedisCluster.sunion(keyA, keyB));//����
    }


    /**
     * sortedSet����
     */
    @Test
    public void sortedSetTest() throws InterruptedException {
        String keyA = "{"+prefix + KEY_SPLIT + "sortedSet}a";
        String keyB = "{"+prefix + KEY_SPLIT + "sortedSet}b";
        String keyC = "{"+prefix + KEY_SPLIT + "sortedSet}c";

        jedisCluster.del(keyA);
        jedisCluster.del(keyB);

        System.out.println(jedisCluster.zadd(keyA, 10, "aa"));
        Map<String, Double> map = new HashMap<>();
        map.put("b", 8.0);
        map.put("c", 4.0);
        map.put("d", 6.0);
        System.out.println(jedisCluster.zadd(keyA, map));
        System.out.println(jedisCluster.zcard(keyA));//�������� key ��������
        System.out.println(jedisCluster.zcount(keyA, 3, 8));//�������� key ��scoreĳ����Χ��������
        System.out.println("zrange: "+jedisCluster.zrange(keyA, 0, -1));//�������� key �У�ָ�������ڵĳ�Ա����score��С����
        System.out.println("zrevrange: "+jedisCluster.zrevrange(keyA, 0, -1));//�������� key �У�ָ�������ڵĳ�Ա����score�Ӵ�С
        System.out.println("zrangeWithScores: "+jedisCluster.zrangeWithScores(keyA, 0, -1));//�������� key �У�ָ�������ڵĳ�Ա����score��С����.������ֵ

        System.out.println("zscore: "+jedisCluster.zscore(keyA, "aa"));

        /*
        �������� key �У����� score ֵ���� min �� max ֮��(�������� min �� max )�ĳ�Ա�����򼯳�Ա�� score ֵ����(��С����)�������С�
        ������ͬ score ֵ�ĳ�Ա���ֵ���(lexicographical order)������(�������������ṩ�ģ�����Ҫ����ļ���)��
         */
        System.out.println("zrangeByScore: "+jedisCluster.zrangeByScore(keyA, 3, 8));
        System.out.println("zrank: "+jedisCluster.zrank(keyA, "c"));//�������� key �г�Ա member ���������������򼯳�Ա�� score ֵ����(��С����)˳�����С�
        System.out.println("zrevrank: "+jedisCluster.zrevrank(keyA, "c"));//�������� key �г�Ա member ���������������򼯳�Ա�� score ֵ����(�Ӵ�С)˳�����С�

        System.out.println("zrem: "+jedisCluster.zrem(keyA, "c", "a"));//�Ƴ����� key �е�һ��������Ա�������ڵĳ�Ա�������ԡ�
        System.out.println("zrange: "+jedisCluster.zrange(keyA, 0, -1));



        System.out.println("zremrangeByRank: "+jedisCluster.zremrangeByRank(keyA, 1, 2));//���±�ɾ��
        System.out.println("zrange: "+jedisCluster.zrange(keyA, 0, -1));
        System.out.println("zremrangeByScore: "+jedisCluster.zremrangeByScore(keyA, 1, 3));//������ɾ��
        System.out.println("zrange: "+jedisCluster.zrange(keyA, 0, -1));

        /*
        �������⼸����������Ҫʹ��"{}"ʹ��key�䵽ͬһ��slot�вſ���
         */
        System.out.println("-------");
        System.out.println(jedisCluster.zadd(keyB, map));
        System.out.println("zrange: "+jedisCluster.zrange(keyB, 0, -1));
        /*
        ZUNIONSTORE destination numkeys key [key ...] [WEIGHTS weight [weight ...]] [AGGREGATE SUM|MIN|MAX]
���������һ���������򼯵Ĳ��������и��� key ������������ numkeys ����ָ���������ò���(�����)���浽 destination ��
Ĭ������£��������ĳ����Ա�� score ֵ�����и������¸ó�Ա score ֵ֮ �� ��
WEIGHTS
ʹ�� WEIGHTS ѡ������Ϊ ÿ�� �������� �ֱ� ָ��һ���˷�����(multiplication factor)��ÿ���������򼯵����г�Ա�� score ֵ�ڴ��ݸ��ۺϺ���(aggregation function)֮ǰ��Ҫ�ȳ��Ը����򼯵����ӡ�
���û��ָ�� WEIGHTS ѡ��˷�����Ĭ������Ϊ 1 ��
AGGREGATE
ʹ�� AGGREGATE ѡ������ָ�������Ľ�����ľۺϷ�ʽ��
Ĭ��ʹ�õĲ��� SUM �����Խ����м�����ĳ����Ա�� score ֵ֮ �� ��Ϊ������иó�Ա�� score ֵ��ʹ�ò��� MIN �����Խ����м�����ĳ����Ա�� ��С score ֵ��Ϊ������иó�Ա�� score ֵ�������� MAX ���ǽ����м�����ĳ����Ա�� ��� score ֵ��Ϊ������иó�Ա�� score ֵ��
         */
        System.out.println("zunionstore: "+jedisCluster.zunionstore(keyC, keyA, keyB));//�ϲ�keyA��keyB�����浽keyC��
        System.out.println("zrange: "+jedisCluster.zrange(keyC, 0, -1));
        System.out.println("zinterstore: "+jedisCluster.zinterstore(keyC, keyA, keyB));//����
        System.out.println("zrange: "+jedisCluster.zrange(keyC, 0, -1));
    }

    /**
     * �б� ����
     */
    @Test
    public void sort() throws InterruptedException {
        String key = prefix + KEY_SPLIT + "queue";
        jedisCluster.del(key);

        System.out.println(jedisCluster.lpush(key, "1", "5", "3", "20", "6"));
        System.out.println(jedisCluster.lrange(key, 0, -1));

        System.out.println(jedisCluster.sort(key));
        System.out.println(jedisCluster.lrange(key, 0, -1));
    }



    /**
     * lua�ű�
     */
    @Test
    public void script() throws InterruptedException {

        /*
        * ���� "return {KEYS[1],KEYS[2],ARGV[1],ARGV[2]}" �Ǳ���ֵ�� Lua �ű������� 2 ָ���˼���������������
        * key1 �� key2 �Ǽ����������ֱ�ʹ�� KEYS[1] �� KEYS[2] ���ʣ������� first �� second ���Ǹ��Ӳ���������ͨ�� ARGV[1] �� ARGV[2] �������ǡ�
        *
        * ע�⣬����һЩ������������redis-cluster����Ҫ������Ϊ��ͬ��key�����䵽�˲�ͬ��slot��
        */
        Object eval = jedisCluster.eval("return {KEYS[1],ARGV[1],ARGV[2]}", 1, "lua", "key1", "dd");
        System.out.println(eval);

        //�ű���ʹ�õ����м���Ӧ���� KEYS ���������ݣ�
        //��Ϊ�����е� Redis �����ִ��֮ǰ���ᱻ������������ȷ����������Щ�����в�������ˣ����� EVAL ������˵������ʹ����ȷ����ʽ�����ݼ�������ȷ������������ȷ��ִ��
        System.out.println(jedisCluster.eval("return redis.call('set', KEYS[1], ARGV[1])", 1, "luaTest", "cv"));
        System.out.println(jedisCluster.get("luaTest"));

        //ע��������Ҫָ��KEY����Ϊ����lua�ű�Ҳ�Ǻ�slot�ҹ���
        String scriptLoad = jedisCluster.scriptLoad("return redis.call('get', KEYS[1])", "luaTest");//���ؽű�
        System.out.println(scriptLoad);//���ص�SHA1У��ͣ���������ֱ��ʹ��������в�����
        System.out.println(jedisCluster.scriptExists(scriptLoad, "luaTest"));//����Ƿ����

        System.out.println(jedisCluster.evalsha(scriptLoad, 1, "luaTest"));//ִ��lua�ű�

        System.out.println(jedisCluster.scriptFlush("luaTest".getBytes()));//ɾ��KEY as  �ϵ�����lua�ű�
        System.out.println(jedisCluster.scriptExists(scriptLoad, "luaTest"));
        System.out.println(jedisCluster.evalsha(scriptLoad, 1, "luaTest"));//�ű��Ѿ�ɾ�������ش���NOSCRIPT No matching script. Please use EVAL.
    }


    /**
     * redis�е�lua�ű����˺ܶ����ƣ���ֹ����Եķ���������lua�ű��з��ص���������ļ��ϡ�
     * ����� http://doc.redisfans.com/script/eval.html - �������ű�
     */
    @Test
    public void scriptFuc() throws InterruptedException {
        String key = "luaTest";
        System.out.println(jedisCluster.del(key));
        System.out.println(jedisCluster.sadd(key, "10","3","7","40","6"));
        System.out.println(jedisCluster.smembers(key));//������ô���ص�ֵ������ģ�  [3, 6, 7, 10, 40]
        System.out.println(jedisCluster.eval("return redis.call('smembers', KEYS[1])", 1, key));//������ĸ������  [10, 3, 40, 6, 7]
    }


    /**
     * ʹ��lua�ű���¼��־
     * @throws InterruptedException
     */
    @Test
    public void redisLog() throws InterruptedException {
        //����������Ҫ��key������
        System.out.println(jedisCluster.eval("redis.log(redis.LOG_WARNING, 'Something is wrong with this script.')", 1, "afd"));

    }



    /**
     * ģ���Ƚ��ȳ� ���� ����
     * �ο� http://www.cnblogs.com/stephen-liu74/archive/2012/02/14/2351859.html
     *      https://www.v2ex.com/t/65663
     */
    @Test
    public void queue1() throws InterruptedException {

        String key = prefix + KEY_SPLIT + "queue";
        Long del = jedisCluster.del(key);
        System.out.println(del);
        //���Ƕ�����г�����5
        int length = 5;

        System.out.println(jedisCluster.lpush(key, "1", "2", "3", "4", "5", "6", "7"));

        List<String> list = jedisCluster.lrange(key, 0, -1);//��ӡ��������
        System.out.println(list);

        Long llen = jedisCluster.llen(key);
        System.out.println("Ŀǰ���г��ȣ�" + llen);

        /**
         * �����������ָ����Χ�ڵ�Ԫ��
         * ÿ��lpush�Ժ󣬾���ltrim���н�ȡ���Ѵﵽ�������У��򶨳�list����Ŀ��
         *
         * ע�⣺
         *      �������ʵ��ҵ�����󣬳����˲�һ����ȡ������Ҳ���Խ��з������������������������������
         */
        System.out.println(jedisCluster.ltrim(key, 0, length - 1));
        System.out.println(jedisCluster.lrange(key, 0, -1));
    }

    /**
     * �ֲ�ʽ������
     * һ����ͨ�� set nx ex ʵ�ֵ�
     * ��������������������ο�  http://ifeve.com/redis-lock/
     */
    @Test
    public void lock() throws  InterruptedException{
        String key = prefix + KEY_SPLIT + "lock";
        /**
         * �������    SET KEY VALUE [EX seconds] [PX milliseconds] [NX|XX]
         */
        System.out.println(jedisCluster.set(key, "����ID", "nx", "ex", 3));

        for (int i = 0 ; i < 6 ; i ++) {
            System.out.println(jedisCluster.get(key));
            Thread.sleep(1000);
        }
    }


    @After
    public void after(){
        try {
            if(jedisCluster != null) jedisCluster.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}