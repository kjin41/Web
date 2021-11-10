import list from "./views/list.js";
import detail from "./views/detail.js";

const router=new VueRouter({
  mode: 'history',
  routes:[
    {
      path: '/',
      component: list,
    },
    {
      path: '/detail/:id/:perPage',
      component: detail,
    },
  ]
});

const app=new Vue({
  el: "#app",
  router,
});
