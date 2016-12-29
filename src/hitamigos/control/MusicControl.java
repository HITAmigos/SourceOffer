package hitamigos.control;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import hitamigos.entity.HibernateUtil;
import hitamigos.entity.music;

public class MusicControl {
  public static boolean insert(music v) {
    boolean result = true;
    Session session = HibernateUtil.currentSession();
    Transaction tran = null;
    try {
      tran = session.beginTransaction();
      session.save(v);
      session.getTransaction().commit();
    } catch (HibernateException e) {
      result = false;
      if (tran != null) {
        tran.rollback();
      }
      e.printStackTrace();
    } finally {
      HibernateUtil.closeSession();
    }
    return result;
  }

  public static List<music> getMusicSet() {
    Session session = HibernateUtil.currentSession();
    Transaction tran = null;
    List<music> v = null;
    try {
      tran = session.beginTransaction();
      v = session.createQuery("FROM music").list();
    } catch (HibernateException e) {
      if (tran != null) {
        tran.rollback();
      }
      e.printStackTrace();
    } finally {
      HibernateUtil.closeSession();
    }
    return v;
  }
  public static music getRecord(String title) {
    List<music> vSet = getMusicSet();
    music v = null;
    for (int i = 0; i < vSet.size(); i++) {
      if (vSet.get(i).getTitle().equals(title)) {
        v = vSet.get(i);
        break;
      }
    }
    return v;
  }

  public static boolean DeleteRecord(music v) {
    boolean result = true;
    Session session = HibernateUtil.currentSession();
    Transaction tran = null;
    try {
      tran = session.beginTransaction();
      session.delete(v);
      session.getTransaction().commit();
    } catch (HibernateException e) {
      result = false;
      if (tran != null) {
        tran.rollback();
      }
      e.printStackTrace();
    } finally {
      HibernateUtil.closeSession();
    }
    return result;
  }

}
