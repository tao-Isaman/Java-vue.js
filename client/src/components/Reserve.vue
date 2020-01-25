<template>
    <div>
        <v-card
                class="mx-auto"
                max-width="1200"
                outlined
                raised
                shaped
        >

            <v-container class = "back">
                <v-row justify="center">
                <v-col cols="4">
                    <v-form v-model="valid" ref="form">
                        <v-row justify="center">
                            <v-col cols="10">
                                <v-text-field
                                        outlined
                                        label="Patient ID"
                                        v-model="reservation.patientId"
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
                            <v-row>
                                <v-col cols="10">
                                    <v-select
                                            label="พยาบาล"
                                            outlined
                                            v-model="reservation.nurseId"
                                            :items="nurses"
                                            item-text="name"
                                            item-value="id"
                                            :rules="[(v) => !!v || 'Item is required']"
                                            required
                                    ></v-select>
                                </v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="10">
                                    <v-select
                                            label="เตียงผู้ป่วย"
                                            outlined
                                            v-model="reservation.bedId"
                                            :items="beds"
                                            item-text="type"
                                            item-value="id"
                                            :rules="[(v) => !!v || 'Item is required']"
                                            required
                                    ></v-select>
                                </v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="10">
                                    <v-select
                                            label="แผนกการรักษา"
                                            outlined
                                            v-model="reservation.departmentId"
                                            :items="departments"
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
                                                v-model="reservation.note"
                                                :rules="[(v) => !!v || 'Item is required']"
                                                required
                                        ></v-text-field>
                                    </v-col>
                                </v-row>


                            <p>วันที่เข้าพัก</p>
                            <v-row justify="center">
                                <v-date-picker v-model="reservation.checkDate" xml:lang="en" type="date" format="YYYY-MM-dd"></v-date-picker>
                            </v-row>
                            <br>
                            <br>

                            <v-row justify="center">
                                <v-col cols="12">
                                    <v-btn @click="saveReservation" :class="{ red: !valid, green: valid }">submit</v-btn>
                                    <v-btn style="margin-left: 15px;" @click="clear">clear</v-btn>
                                </v-col>
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
    import http from "../http-common";

    export default {
        name: "reservation",
        data() {
            return {
                reservation: {
                    patientId: "",
                    nurseId: "",
                    bedId: "",
                    departmentId: "",
                    checkDate:"",
                     note:"",
                },

                valid: false,
                patientCheck: false,
                patientName: "",
                beds:[],
                departments:[],
                nurses:[],
            };
        },
        methods: {
            /* eslint-disable no-console */

            // ดึงข้อมูล nurse ใส่ combobox
            getNurse() {
                http
                    .get("/nurse")
                    .then(response => {
                        this.nurses = response.data;
                        console.log(response.data);
                    })
                    .catch(e => {
                        console.log(e);
                    });
            },
            // ดึงข้อมูล department ใส่ combobox
            getDepartment() {
                http
                    .get("/department")
                    .then(response => {
                        this.departments = response.data;
                        console.log(response.data);
                    })
                    .catch(e => {
                        console.log(e);
                    });
            },
            // ดึงข้อมูล bed ใส่ combobox
            getBed() {
                http
                    .get("/bed")
                    .then(response => {
                        this.beds = response.data;
                        console.log(response.data);
                    })
                    .catch(e => {
                        console.log(e);
                    });
            },
            // function ค้นหาผู้ป่วยด้วย ID
            findPatient() {
                http
                    .get("/patient/" + this.reservation.patientId)
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
            // function เมื่อกดปุ่ม บันทึก
            saveReservation() {
                http
                    .post(
                        "/reservationPost/" +
                        this.reservation.patientId +
                        "/" +
                        this.reservation.nurseId +
                        "/" +
                        this.reservation.bedId +
                        "/" +
                        this.reservation.departmentId,
                        this.reservation

                    )
                    .then(response => {
                        console.log(response);
                        alert("บันทึกสำเร็จ");
                        this.clear();
                    })
                    .catch(e => {
                        console.log(e);
                    });
                this.submitted = true;
            },
            clear() {
                this.$refs.form.reset();
                this.patientCheck = false;
            },
            refreshList() {
                this.getNurse();
                this.getBed();
                this.getDepartment();
            }
            /* eslint-enable no-console */
        },
        mounted() {
            this.getNurse();
            this.getBed();
            this.getDepartment();
        }
    };
</script>
<style>
    .font {
        font-family: 'Prompt', sans-serif;
        font-size: 30px ;
        color: black;
    }
    .back {
        background-image: linear-gradient(0deg, rgba(255, 0, 150, 0.3), rgba(255, 0, 150, 0.3)), url(../assets/g1.png);
        background-size: cover;
        background-blend-mode: multiply;
    }

</style>>
