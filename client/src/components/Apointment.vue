<template>
    <div>
        <v-card
                class="mx-auto"
                max-width="12000"
                outlined
                raised
                shaped
        >
            <v-container class = "back">
                <v-layout text-center wrap>
                    <v-flex mb-4>
                        <br />
                        <h1 class="font">ระบบนัดหมายผู้ป่วย</h1>
                    </v-flex>
                </v-layout>
                <v-row justify="center">
                    <v-col cols="4">
                        <v-form v-model="valid" ref="form">
                            <v-row justify="center">
                                <v-col cols="10">
                                    <v-text-field
                                            outlined
                                            label="Patient ID"
                                            v-model="appointment.patientId"
                                            :rules="[(v) => !!v || 'Item is required']"
                                            required
                                    ></v-text-field>
                                    <p v-if="patientCheck != ''">Patient Name : {{patientName}}</p>
                                </v-col>

                                <v-col cols="2">
                                    <div class="my-2">
                                        <v-btn @click="findPatient" depressed large color="primary">Search</v-btn>
                                    </div>
                                </v-col>
                            </v-row>



                            <div v-if="patientCheck">
                                <p>เลือกวันที่นัดหมาย</p>
                                <v-row justify="center">
                                    <v-date-picker v-model="appointment.date" xml:lang="en" type="date" format="YYYY-MM-dd"></v-date-picker>
                                </v-row>

                                <v-row justify="center">
                                    <v-col cols="10">
                                        <v-select
                                                label="สาเหตุที่นัดหมาย"
                                                outlined
                                                v-model="appointment.typecauseId"
                                                :items="typecauseSelect"
                                                item-text="typecauseSelect"
                                                item-value="id"
                                                :rules="[(v) => !!v || 'Item is required']"
                                                required
                                        ></v-select>
                                    </v-col>
                                </v-row>

                                <v-row justify="center">
                                    <v-col cols="10">
                                        <v-select
                                                label="การเดินทาง"
                                                outlined
                                                v-model="appointment.typegoingId"
                                                :items="typegoingSelect"
                                                item-text="typegoingSelect"
                                                item-value="id"
                                                :rules="[(v) => !!v || 'Item is required']"
                                                required
                                        ></v-select>
                                    </v-col>
                                </v-row>

                                <v-row justify="center">
                                    <v-col cols="10">
                                        <v-select
                                                label="แผนกที่นัดหมาย"
                                                outlined
                                                v-model="appointment.departmentId"
                                                :items="departmentlSelect"
                                                item-text="departmentlSelect"
                                                item-value="id"
                                                :rules="[(v) => !!v || 'Item is required']"
                                                required
                                        ></v-select>
                                    </v-col>
                                </v-row>

                                <v-row justify="center">
                                    <v-col cols="10">
                                        <v-text-field
                                                outlined
                                                label="ข้อมูลเพิ่มเติม"
                                                v-model="appointment.additional"
                                                :rules="[(v) => !!v || 'Item is required']"
                                                required
                                        ></v-text-field>

                                        <v-sheet class="text-center" height="750px" wide="300" >
                                            <div id="printMe">
                                                <v-container>
                                                    <v-row justify="center">
                                                        <v-col justify="center" cols="10">
                                                            <v-form v-model="valid" ref="form" >
                                                                <!-- SOURCE -->
                                                                <h1>รายละเอียดการนัดหมายผู้ป่วย</h1> <br>
                                                                <v-row>
                                                                    <v-col cols="5">
                                                                        <p class="text-left"><strong>รหัสผู้ป่วย : </strong> {{this.appointment.patientId}}</p>
                                                                    </v-col>
                                                                    <v-col>
                                                                        <p class="text-left" ><strong>ชื่อผู้ป่วย :</strong> {{this.patientName}}</p>
                                                                    </v-col>
                                                                </v-row>
                                                                <v-row>
                                                                    <v-col cols="5">
                                                                        <p class="text-left"><strong>สาเหตุที่นัด เพราะ : </strong>{{this.typecauseName}}  </p>
                                                                    </v-col>
                                                                    <v-col cols="5">
                                                                        <p class="text-left"><strong>การเดินทางโดย : </strong>{{this.typegoingName}} </p>
                                                                    </v-col>
                                                                </v-row>
                                                                <v-row >
                                                                    <v-col cols="5">
                                                                        <p class="text-left"><strong>แผนกที่นัดหมาย :  </strong> {{this.departmentName}}</p>
                                                                    </v-col>
                                                                    <v-col cols="5">
                                                                        <p class="text-left"><strong>วันที่นัดหมาย : </strong>{{this.appointment.date}}  </p>
                                                                    </v-col>
                                                                </v-row>
                                                                <v-row>
                                                                    <v-col cols="5">
                                                                        <p class="text-left"><strong>รายละเอียดเพิ่มเติม : </strong>{{this.appointment.additional}} </p>
                                                                    </v-col>
                                                                </v-row>


                                                            </v-form>
                                                        </v-col>
                                                    </v-row>
                                                </v-container>
                                            </div>

                                        </v-sheet>
                                    </v-col>
                                </v-row>



                                <v-row justify="center">

                                    <div class="text-center">
                                        <v-bottom-sheet v-model="sheet">
                                            <template v-slot:activator="{ on }">
                                                <v-btn @click="findTypeCause" >สรุปรายละเอียด</v-btn>
                                                <v-btn style="margin-left: 15px;" @click="clear">clear</v-btn>
                                                <v-btn @click="saveVideoRental" :class="{ red: !valid, green: valid }">submit</v-btn>
                                                <v-row justify="center">
                                                    <v-btn color="primary" dark  @click="print">พิมพ์</v-btn>
                                                </v-row>
                                            </template>

                                        </v-bottom-sheet>
                                    </div>
                                </v-row>
                                <br />
                            </div>
                        </v-form>
                    </v-col>
                </v-row>




            </v-container>
        </v-card>
    </div>
</template>

<script>
    import http from "../http-common"
    import VueHtmlToPaper from 'vue-html-to-paper'
    import DatePicker from 'vuetify'
    import Vue from 'vue';

    const options = {
        name: '_blank',
        specs: [
            'fullscreen=yes',
            'titlebar=yes',
            'scrollbars=yes'
        ],
        styles: [
            'https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css',
            'https://unpkg.com/kidlat-css/css/kidlat.css'
        ]
    }

    Vue.use(VueHtmlToPaper, options);

    // eslint-disable-next-line no-unused-vars

    export default {
        name: "appointment",
        components: {
            // eslint-disable-next-line vue/no-unused-components
            DatePicker
        },
        data() {
            return {
                appointment: {
                    patientId: "",
                    typecauseId: "",
                    typegoingId: "",
                    departmentId: "",

                    additional: "",
                    date: ""
                },
                sheet: false,
                valid: false,
                patientCheck: false,
                patientName: "",
                typeCauseName:"",
                typeGoingName:"",
                departmentName:"",
            };
        },
        methods: {

            print() {
                if(this.saveVideoRental == true) {
                    this.$htmlToPaper('printMe');
                    const options3 = { title: "Alert", size: "sm" };
                    this.$dialogs.alert("พิมพ์ใบนัดหมายผู้ป่วยสำเร็จ", options3);
                }else if(this.saveVideoRental == false) {
                    const options4 = { title: "Alert", size: "sm" };
                    this.$dialogs.alert("พิมพ์ใบนัดหมายผู้ป่วยไม่สำเร็จ", options4);

                }
            },


            /* eslint-disable no-console */

            // ดึงข้อมูล Employee ใส่ combobox
            getCreatTypeCause() {
                http
                    .get("/typecause")
                    .then(response => {
                        this.typecauseSelect = response.data;
                        console.log(response.data);
                    })
                    .catch(e => {
                        console.log(e);
                    });
            },
            // ดึงข้อมูล RentalType ใส่ combobox
            getCreatTypeGoing() {
                http
                    .get("/typegoing")
                    .then(response => {
                        this.typegoingSelect= response.data;
                        console.log(response.data);
                    })
                    .catch(e => {
                        console.log(e);
                    });
            },
            // ดึงข้อมูล Video ใส่ combobox
            getCreatDepartment() {
                http
                    .get("/department")
                    .then(response => {
                        this.departmentlSelect = response.data;
                        console.log(response.data);
                    })
                    .catch(e => {
                        console.log(e);
                    });
            },
            // function ค้นหาลูกค้าด้วย ID
            findPatient() {
                http
                    .get("/patient/" + this.appointment.patientId)
                    .then(response => {
                        console.log(response);
                        if (response.data != null) {
                            this.patientName = response.data.name;
                            this.patientCheck = response.status;
                        } else {
                            this.clear()
                        }
                    })
                    .catch(e => {
                        console.log(e);
                    });
                this.submitted = true;
            },
            /* eslint-disable */
            findTypeCause() {
                http
                    .get("/typecause/" + this.appointment.typecauseId)
                    .then(response => {
                        console.log(response);
                        this.typecauseName = response.data.typecauseSelect;
                        this.findTypeGoing();
                        this.findDepartment();
                        console.log(typecauseName);
                    })
                    .catch(e => {
                        console.log(e);
                    });
            },
            findTypeGoing() {
                http
                    .get("/typegoing/" + this.appointment.typegoingId)
                    .then(response => {
                        console.log(response);
                        this.typegoingName = response.data.typegoingSelect;
                        console.log(typegoingName);
                    })
                    .catch(e => {
                        console.log(e);
                    });

            },
            findDepartment() {
                http
                    .get("/department/" + this.appointment.departmentId)
                    .then(response => {
                        console.log(response);
                        this.departmentName = response.data.departmentlSelect;
                        console.log(departmentName);
                    })
                    .catch(e => {
                        console.log(e);
                    });
            },

            // function เมื่อกดปุ่ม submit
            saveVideoRental() {
                http
                    .post(
                        "/appointment/" +
                        this.appointment.patientId +
                        "/" +
                        this.appointment.typecauseId +
                        "/" +
                        this.appointment.typegoingId +
                        "/" +
                        this.appointment.departmentId,
                        this.appointment
                    )
                    .then(response => {
                        console.log(response);
                        this.saveVideoRental = true;
                        const options1 = { title: "Alert", size: "sm" };
                        this.$dialogs.alert("บันทึกข้อมูลสำเร็จ", options1);
                    })
                    .catch(e => {
                        console.log(e);
                        this.saveVideoRental = false;
                        const options2 = { title: "Alert", size: "sm" };
                        this.$dialogs.alert("บันทึกข้อมูลไม่สำเร็จ", options2);
                    });
                this.submitted = true;
            },
            clear() {
                this.$refs.form.reset();
                this.patientCheck = false;
            },
            refreshList() {
                this.getCreatTypeCause();
                this.getCreatTypeGoing();
                this.getCreatDepartment();
                this.findTypeCause();
                this.findTypeGoing();
                this.findDepartment();
            }
            /* eslint-enable no-console */
        },
        mounted() {
            this.getCreatTypeCause();
            this.getCreatTypeGoing();
            this.getCreatDepartment();
            this.findTypeCause();
            this.findTypeGoing();
            this.findDepartment();
        }
    };
</script>
<style>
    .font {
        font-family: 'Prompt', sans-serif;
        font-size: 30px ;
        color: white;
    }
    .back {
        background-image: linear-gradient(0deg, rgba(255, 0, 150, 0.3), rgba(255, 0, 150, 0.3)), url(../assets/g1.png);
        background-size: cover;
        background-blend-mode: multiply;
    }

</style>>