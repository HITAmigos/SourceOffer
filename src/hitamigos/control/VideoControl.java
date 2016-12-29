package hitamigos.control;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import hitamigos.entity.HibernateUtil;
import hitamigos.entity.video;

public class VideoControl {
  public static boolean insert(video v) {
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

  public static List<video> getVideoSet() {
    Session session = HibernateUtil.currentSession();
    Transaction tran = null;
    List<video> v = null;
    try {
      tran = session.beginTransaction();
      v = session.createQuery("FROM video").list();
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
  public static video getRecord(String title) {
    List<video> vSet = getVideoSet();
    video v = null;
    for (int i = 0; i < vSet.size(); i++) {
      if (vSet.get(i).getTitle().equals(title)) {
        v = vSet.get(i);
        break;
      }
    }
    return v;
  }

  public static boolean DeleteRecord(video v) {
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
