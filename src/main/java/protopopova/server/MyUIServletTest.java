package protopopova.server;

import org.hibernate.Session;

import org.junit.Assert;
import org.junit.Test;
import protopopova.model.BookEntity;

import java.util.List;


public class MyUIServletTest {
    private MyUIServlet servlet = new MyUIServlet();


    @Test
    public void doSelect() {
        List<BookEntity> list = servlet.doSelect();
        Assert.assertTrue(list.size()>0);

    }

    @Test
    public void doDelete() {
        BookEntity entity = new BookEntity();
        entity.setId(24);
        List<BookEntity> listOld = servlet.doSelect();
        servlet.doDelete(entity);
        List<BookEntity> listNew = servlet.doSelect();
        Assert.assertEquals(listNew.size(), listOld.size()-1);
    }

    @Test
    public void doInsert() {
        BookEntity entity = new BookEntity();
        entity.setAuthor("Ольга Громыко");
        entity.setTitle("Космотехнолухи. Том 2");
        entity.setDescription("Выбирай, кого будешь любить");
        entity.setIsbn("978-5-9922-2185-5");
        entity.setPrintYear(2016);
        List<BookEntity> listOld = servlet.doSelect();
        servlet.doInsert(entity);
        List<BookEntity> listNew = servlet.doSelect();
        Assert.assertEquals(listNew.size(), listOld.size()+1);
        System.out.println("@Entity = " + entity.toString());

    }

    @Test
    public void readBook() {
        BookEntity entity = servlet.getById(26);
        Assert.assertEquals(entity.getReadAlready(), false);
        servlet.readBook(entity);
        BookEntity newEntity = servlet.getById(26);
        Assert.assertEquals(newEntity.getReadAlready(), true);

    }

    @Test
    public void update() {
        BookEntity entity = servlet.getById(26);
        System.out.println("@beforUpdate = "+entity.toString());
        entity.setPrintYear(1699);
        entity.setIsbn("111111");
        entity.setDescription("fdsfsdfsdfsdf");
        entity.setTitle("nnneeeee");
        entity.setReadAlready(true);
        BookEntity updateEnt = servlet.update(entity);
        Assert.assertEquals(updateEnt.getPrintYear(), 1699);
        Assert.assertEquals(updateEnt.getId(), 26);
        Assert.assertEquals(updateEnt.getDescription(), "fdsfsdfsdfsdf");
        Assert.assertEquals(updateEnt.getTitle(), "nnneeeee");
        Assert.assertEquals(updateEnt.getReadAlready(), false);
        Assert.assertEquals(updateEnt.getIsbn(), "111111");
        Assert.assertEquals(updateEnt.getAuthor(), "Ольга Громыко");
    }
}
