import Vue from 'vue'
import Router from 'vue-router';
import PostPatient from '../components/PostPatient.vue';
import login from '../components/login.vue';
import home from '../components/home.vue'
import Apointment from "../components/Apointment";
import ExaminationSystem from "../components/ExaminationSystem";
import Reserve  from '../components/Reserve.vue'

Vue.use(Router);

export default new Router({
    mode: 'history',
    base: process.env.BASE_URL,
    routes: [{
            path: '/patient/:id',
            component: PostPatient
        },
        {
        path: '/apointment/:id',
         component: Apointment
        },
        {
            path: '/',
            component: login
        },
        {
            path: '/home/:id',
            component: home
        },
        {
            path: '/examinationSystem/:id',
            component: ExaminationSystem
        },
        {
            path: '/reserve/:id',
            component: Reserve
        },


    ]
});