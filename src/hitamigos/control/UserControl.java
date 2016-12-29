package hitamigos.control;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import hitamigos.entity.HibernateUtil;
import hitamigos.entity.User;


public class UserControl {
  public static List<User> getUserSet() {
    Session session = HibernateUtil.currentSession();
    Transaction tran = null;
    List<User> Users = null;
    try {
      tran = session.beginTransaction();
      Users = session.createQuery("FROM User").list();
    } catch (HibernateException e) {
      if (tran != null) {
        tran.rollback();
      }
      e.printStackTrace();
    } finally {
      HibernateUtil.closeSession();
    }
    return Users;
  }
  public static User getUser(String username) {
    List<User> UserSet = getUserSet();
    User user = null;
    if (UserSet != null) {
      for (int i = 0; i < UserSet.size(); i++) {
        if (UserSet.get(i).getUsername().equals(username)) {
          user = UserSet.get(i);
          break;
        }
      }
    }
    return user;
  }
}
