import java.util.*;
import java.io.*;
public class Book{
    public String book_name;
    public String book_author;
    public String book_genre;
    public int id;
    public int price;
    Book(int id, String bookname, String bookauthor, String bookgenre, int price){
      this.id = id;
      this.book_name = bookname;
      this.book_author = bookauthor;
      this.book_genre = bookgenre;
      this.price = price;
    }
    public String toString(){
    String foo =  this.book_name + " " + this.price; //example
    return foo;
  }
}
