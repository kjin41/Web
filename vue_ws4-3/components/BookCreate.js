export default{
    template: `
    <div class="regist">
      <h1 class="underline">SSAFY 도서 등록</h1>
        <div class="regist_form">
          <label for="isbn">도서번호</label>
          <input type="text" id="isbn" name="isbn" v-model="isbn" ref="isbn"/><br />
          <label for="title">도서명</label>
          <input type="text" id="title" name="title" v-model="title" ref="title"/><br />
          <label for="author">저자</label>
          <input type="text" id="author" name="author" v-model="author" ref="author"/><br />
          <label for="price">가격</label>
          <input type="number" id="price" name="price" v-model="price" ref="price"/><br />
          <label for="content">설명</label>
          <br />
          <textarea id="content" name="content" cols="35" rows="5" v-model="content" ref="content"></textarea><br />
          <button @click="checkValue()" class="btn">등록</button>
          <button @click="moveList()" class="btn">목록</button>
        </div>
    </div>
    `,

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
            axios.post("http://localhost:9999/vuews/book",{
                isbn: this.isbn,
                title: this.title,
                author: this.author,
                price: this.price,
                content: this.content
            }).then(({data})=>{
                let msg="책 등록시 문제 발생";
                if (data==="success"){
                    msg="등록 완료";
                }
                alert(msg);
                this.moveList();
            })
        },
        moveList(){
            // location.href="#/list";
            this.$router.push("/list");
        }
    }
    
}