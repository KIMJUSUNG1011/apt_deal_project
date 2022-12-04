import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: () => import("@/components/main/Main"),
  },
  {
    path: "/propertyList",
    name: "propertyList",
    component: () => import("@/components/property/PropertyList"),
  },
  {
    path: "/noticeList",
    name: "noticeList",
    component: () => import("@/components/notice/NoticeList"),
  },
  {
    path: "/userDetail",
    name: "userDetail",
    component: () => import("@/components/user/UserDetail"),
  },
  {
    path: "/userRegist",
    name: "userRegist",
    component: () => import("@/components/user/UserRegist"),
  },
  {
    path: "/userLogin",
    name: "userLogin",
    component: () => import("@/components/user/UserLogin"),
  },
  {
    path: "/userFindPass",
    name: "userFindPass",
    component: () => import("@/components/user/UserFindPass"),
  },
  {
    path: "/noticeDetail",
    name: "noticeDetail",
    component: () => import("@/components/notice/NoticeDetail"),
  },
  {
    path: "/propertyDetail",
    name: "propertyDetail",
    component: () => import("@/components/property/PropertyDetail"),
  },
  {
    path: "/contact",
    name: "contact",
    component: () => import("@/components/common/Contact"),
  },
];

const router = new VueRouter({
  mode: "history",
  scrollBehavior() {
    return { x: 0, y: 0 };
  },
  base: process.env.BASE_URL,
  routes,
});

const originalPush = VueRouter.prototype.push;
VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch((err) => {
    if (err.name !== "NavigationDuplicated") throw err;
  });
};

export default router;
