import MainContent from "./components/MainContent.js";
import BookCreate from "./components/BookCreate.js";
import BookDelete from "./components/BookDelete.js";
import BookList from "./components/BookList.js";
import BookModify from "./components/BookModify.js";
import BookView from "./components/BookView.js";

Vue.use(VueRouter);
export default new VueRouter({
  mode: "history",
  routes: [
    {
      path: "/",
      name: "main",
      component: MainContent
    },
    {
      path: "/create",
      name: "create",
      component: BookCreate
    },
    {
      path: "/list",
      name: "list",
      component: BookList
    },
    {
      path: "/delete",
      name: "delete",
      component: BookDelete
    },
    {
      path: "/modify",
      name: "modify",
      component: BookModify
    },
    {
      path: "/view",
      name: "view",
      component: BookView
    },
  ]

})