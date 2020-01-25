import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify';
import router from './router';
import 'v-slim-dialog/dist/v-slim-dialog.css'
import SlimDialog from 'v-slim-dialog'


Vue.use(SlimDialog)
//import 'v-slim-dialog/dist/v-slim-dialog.css'
//import SlimDialog from 'v-slim-dialog'

//Vue.use(SlimDialog)
Vue.config.productionTip = false

new Vue({
  router,
  vuetify,
  render: h => h(App)
}).$mount('#app')
