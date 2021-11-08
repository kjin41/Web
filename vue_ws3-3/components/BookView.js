export default{
    template: "#BookView",
    created() {
        const params=new URL(document.location).searchParams;
        let param=params.get("isbn");
        let books=JSON.parse(localStorage.getItem("books"));
        for (let book of books){
          if (book.isbn==param){
            this.book=book;
            console.log(this.book);
            break;
          }
        }
      },
      data(){
          return {
            book: {}
          }
      }
}