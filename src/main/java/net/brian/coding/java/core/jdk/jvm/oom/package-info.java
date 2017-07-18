/**
 * 这个package下主要是各种把虚拟机“玩坏”的方法，总结各种触发GC、触发OOM的情况，并以每种情况的概述作为java文件的命名
 * 需要注意的是：若要通过run configurations配置参数在控制台查看GC日志
 * 请确保先清空run configurations里面的历史纪录，否则可能出现多个历史纪录的GC日志同时出现的情况
 * 
 * 本目录下使用的虚拟机信息通过-version打印如下：
 * java version "1.8.0_91"
 * Java(TM) SE Runtime Environment (build 1.8.0_91-b15)
 * Java HotSpot(TM) 64-Bit Server VM (build 25.91-b15, mixed mode)
 * 
 * 所有示例的类注释的最后都给出这个类运行时虚拟机的参数配置以及相应的日志输出
 */
package net.brian.coding.java.core.jdk.jvm.oom;