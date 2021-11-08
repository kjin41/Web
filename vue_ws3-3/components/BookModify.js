export default{
    template: "#BookModify",
    created() {
        const params=new URL(document.location).searchParams;
        let param=params.get("isbn");
        let books=JSON.parse(localStorage.getItem("books"));
        for (let book of books){
          if (book.isbn==param){
            this.isbn=book.isbn;
            this.title=book.title;
            this.author=book.author;
            this.price=book.price;
            this.content=book.content;
            break;
          }
        }
      },
      data(){
        return {
          isbn: "",
          title: "",
          author: "",
          price: 0,
          content: ""
        }
      },
      methods: {
        list(){
          location.href="./list.html";
        },
        modify(){
        const params=new URL(document.location).searchParams;
        let param=params.get("isbn");
        let books=JSON.parse(localStorage.getItem("books"));
        for (let i=0; i<books.length; i++){
          if (books[i].isbn==param){
            books[i]={
              isbn: this.isbn,
              title: this.title,
              author: this.author,
              price: this.price,
              content: this.content
            }
            break;
          }
        }
          localStorage.setItem("books", JSON.stringify(books));
          location.href="./list.html";
        }
      },
}