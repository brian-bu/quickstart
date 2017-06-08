package net.brian.coding.java.web.ssm.springtrxn.demo;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.brian.coding.java.core.jdk.jvm.deepjvm.PersonBean;
  
@Transactional  
public class UserDaoImplByHibernateTemplate extends JdbcTemplate {
    @Transactional(propagation=Propagation.REQUIRED,rollbackForClassName="Exception")
    public void addUser(PersonBean user) throws Exception {
        this.save(user);
    }

    @Transactional(propagation=Propagation.REQUIRED,rollbackForClassName="Exception")
    public void modifyUser(PersonBean user) {
        this.update(user);
    }

    @Transactional(propagation=Propagation.REQUIRED,rollbackForClassName="Exception")
    public void delUser(String username) {
        this.delete(this.load(PersonBean.class, username));
    }

    @Transactional(readOnly=true)
    public void selectUser() {

    }

}