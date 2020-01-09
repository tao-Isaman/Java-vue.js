import Vue from 'vue'
import Router from 'vue-router';
import PostPatient from '../components/PostPatient.vue';
import login from '../components/login.vue';
import home from '../components/home.vue'
import Apointment from "../components/Apointment";
import ExaminationSystem from "../components/ExaminationSystem";
import Reserve  from '../components/Reserve.vue'
import Discharge  from '../components/Discharge.vue'
import DoctorOrder from '../components/DoctorOrder.vue';
import PostPayment from '../components/PostPayment.vue';
import Bills from '../components/Bills.vue';
import Bill from '../components/Bill.vue'

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
        {
            path: '/discharge/:id',
            component: Discharge
        },
        {
            path: '/DoctorOrder/:id',
            component: DoctorOrder
        }
        ,
        {
            path: '/PostPayment/:id',
            component: PostPayment
        }
        ,
        {
            path: '/Bill/:id',
            component: Bill
        }       
        ,
        {
            path: '/Bills/:id',
            component: Bills
        }    

    ]
});