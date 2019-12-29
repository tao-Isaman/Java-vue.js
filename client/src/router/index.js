import Vue from 'vue'
import Router from 'vue-router';
import PostPatient from '../components/PostPatient.vue';
import login from '../components/login.vue';
import home from '../components/home.vue'

Vue.use(Router);

export default new Router({
    mode: 'history',
    base: process.env.BASE_URL,
    routes: [{
            path: '/patient/:id',
            component: PostPatient
        },
        {
            path: '/',
            component: login
        },
        {
            path: '/home/:id',
            component: home
        },


    ]
});