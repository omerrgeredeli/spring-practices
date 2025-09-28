package library2;

public class Book {
    private int id;
    private String author;
    private String title;
    private int publishedYear;
    private String category;
    private int borrowCount;

    public Book(int id, String author, String title, int publishedYear, String category) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.publishedYear = publishedYear;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getBorrowCount() {
        return borrowCount;
    }

    public void setBorrowCount(int borrowCount) {
        this.borrowCount = borrowCount;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", publishedYear=" + publishedYear +
                ", category='" + category + '\'' +
                ", borrowCount=" + borrowCount +
                '}';
    }

    public int incrementBorrowCount(){
        return this.borrowCount++;
    }
}
