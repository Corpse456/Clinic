import Vue from 'vue'
import App from './App.vue'
import Login from './components/Login.vue'
import MainWindow from './components/MainWindow.vue'
import VueRouter from "vue-router";

Vue.config.productionTip = false;

Vue.use(VueRouter);

const routes = [
    {path: '/login', name: 'Login', component: Login},
    {path: '/window', name: 'MainWindow', component: MainWindow}
];

const router = new VueRouter({
    routes
});

new Vue({
    router: router,
    render: h => h(App),
}).$mount('#app')
