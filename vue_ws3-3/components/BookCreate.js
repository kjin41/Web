export default{
    template: "#BookCreate",

    data() {
        return {
          isbn: "",
          title: "",
          author: "",
          price: "",
          content: "",
        };
    },
    
    methods:{
        checkValue(){
            let err = true;
            let msg = "";
            !this.isbn && ((msg = "isbn 입력해주세요"), (err = false), this.$refs.isbn.focus());
            err && !this.title && ((msg = "제목 입력해주세요"), (err = false), this.$refs.title.focus());
            err && !this.author && ((msg = "저자 입력해주세요"), (err = false), this.$refs.author.focus());
            err && !this.price && ((msg = "가격 입력해주세요"), (err = false), this.$refs.price.focus());
            err && !this.content && ((msg = "내용 입력해주세요"), (err = false), this.$refs.content.focus());
            if (!err) alert(msg);
            else this.register();
        },
        register(){
            let books=new Array();
            if (localStorage.getItem("books")){
            books=JSON.parse(localStorage.getItem("books"));
            }

            let book={
            isbn: this.isbn,
            title: this.title,
            author: this.author,
            price: this.price,
            content: this.content
            };
            console.log(book);
            books.push(book);
            localStorage.setItem("books", JSON.stringify(books));
            alert("책 등록 완료");
            location.href="./list.html";
        },
        list(){
            location.href="./list.html"
        }
    }
    
}