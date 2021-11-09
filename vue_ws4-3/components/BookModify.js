export default{
    template: `
    <div class="regist">
      <h1 class="underline">SSAFY 도서 수정</h1>
      <div class="regist_form">
        <label for="isbn">도서번호</label>
        <input type="hidden" id="isbn" name="isbn" v-model="isbn"/><br />
        <div>{{this.isbn}}</div>
        <label for="title">도서명</label>
        <input type="text" id="title" name="title" v-model="title"/><br />
        <label for="author">저자</label>
        <input type="text" id="author" name="author" v-model="author"/><br />
        <label for="price">가격</label>
        <input type="number" id="price" name="price" v-model="price"/><br />
        <label for="content">설명</label>
        <br />
        <textarea id="content" name="content" cols="35" rows="5" v-model="content"></textarea><br />
        <button @click="modify()">수정</button>
        <button @click="list()">목록</button>
      </div>
    </div>
    `,
    created() {
      axios.get(`http://localhost:9999/vuews/book/${this.$route.query.isbn}`).then(({data})=>{
        this.isbn=data.isbn,
        this.title=data.title,
        this.author=data.author,
        this.price=data.price,
        this.content=data.content
      })
    },
    data(){
      return {
        isbn: "",
        title: "",
        author: "",
        price: 0,
        content: "",
      }
    },
    methods: {
      checkValue(){
        let err = true;
        let msg = "";
        !this.title && ((msg = "제목 입력해주세요"), (err = false), this.$refs.title.focus());
        err && !this.author && ((msg = "저자 입력해주세요"), (err = false), this.$refs.author.focus());
        err && !this.price && ((msg = "가격 입력해주세요"), (err = false), this.$refs.price.focus());
        err && !this.content && ((msg = "내용 입력해주세요"), (err = false), this.$refs.content.focus());
        if (!err) alert(msg);
        else this.register();
    },

      list(){
        this.$router.push("/list");
      },

      modify(){
        axios.put(`http://localhost:9999/vuews/book/${this.$route.query.isbn}`, {
          isbn: this.isbn,
          title: this.title,
          author: this.author,
          price: this.price,
          content: this.content
          
        }).then(({data})=>{
          let msg="수정시 문제 발생";
          if (data==="success"){
            msg="수정 성공";
          }
          alert(msg);
          this.list();
        })
      }
    },
}