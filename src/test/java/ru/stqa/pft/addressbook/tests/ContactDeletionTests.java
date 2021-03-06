package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions (){
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withFirstName("for").withLastName("delete"), true);
    }
  }

  @Test
  public void testContactDeletion(){
    Contacts before = app.contact().all();
    ContactData contact_for_remove = before.iterator().next();
    app.contact().delete(contact_for_remove);
    assertThat(app.contact().getContactCount(), equalTo(before.size() - 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.without(contact_for_remove)));
  }
}
