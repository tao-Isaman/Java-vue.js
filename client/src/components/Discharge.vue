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
                <v-layout text-center wrap>
                    <v-flex mb-4>
                        <br />
                        <h1 class="font">ระบบนำจำหน่ายผู้ป่วย</h1>
                    </v-flex>
                </v-layout>
                <v-row justify="center">
                    <v-col cols="10">
                        <v-form v-model="valid" ref="form">
                            <v-row>
                                <v-col cols="10">
                                    <v-text-field
                                            outlined
                                            label="รหัสบัตรประชาชน"
                                            v-model="discharge.nationalId"
                                            :rules="[(v) => !!v || 'Item is required']"
                                            required
                                    ></v-text-field>
                                    <p v-if="patientCheck != ''">Patient Name : {{patientName}}</p>
                                </v-col>
                                
                                <v-col cols="2">
                                    <div class="my-2">
                                        <v-btn @click="findPatient" depressed large color="primary">ค้นหา</v-btn>
                                    </div>
                                </v-col>
                            </v-row>

                            <div v-if="patientCheck">
                                <h1 class="display-2 font-weight mb-3">สถานะผู้ป่วยก่อนนำจำหน่าย</h1>

                                <v-col cols="10">
                                    <v-select
                                            label="ระดับความรู้สึกตัว"
                                            outlined
                                            v-model="discharge.sensoriumId"
                                            :items="sensorium"
                                            item-text="name"
                                            item-value="id"
                                            :rules="[(v) => !!v || 'Item is required']"
                                            required
                                    ></v-select>
                                    <v-select
                                            label="ความสามารถในการดูแลตนเอง"
                                            outlined
                                            v-model="discharge.selfcareId"
                                            :items="selfcare"
                                            item-text="name"
                                            item-value="id"
                                            :rules="[(v) => !!v || 'Item is required']"
                                            required
                                    ></v-select>
                                    <v-select
                                            label="ประเภทการนำจำหน่าย"
                                            outlined
                                            v-model="discharge.distributionTypeId"
                                            :items="distributionType"
                                            item-text="name"
                                            item-value="id"
                                            :rules="[(v) => !!v || 'Item is required']"
                                            required
                                    ></v-select>
                                    <v-textarea
                                            outlined
                                            label="หมายเหตุ"
                                            v-model="discharge.note"
                                            :rules="[(v) => !!v || 'Item is required']"
                                            required
                                    ></v-textarea>
                                </v-col>

                                <v-row justify="center">
                                    <div class="text-center">
                                        <v-bottom-sheet v-model="sheet">
                                            <template v-slot:activator="{ on }">
                                                <v-btn prepend-icon="check_box" :class="{ red: !valid, green: valid } " dark v-on="on" @click="findPatient">สรุปรายละเอียด</v-btn>
                                                <v-btn @click="saveDischarge" :class="{ red: !valid, green: valid }">บันทึก</v-btn>
                                                <v-btn style="margin-left: 15px;" @click="clear">ยกเลิก</v-btn>
                                                <v-btn class="ma-2" tile outlined color="success" @click="print">
                                                    <v-icon left>mdi-pencil</v-icon> พิมพ์ใบส่งตัว
                                                </v-btn>

                                            </template>

                                            <v-sheet class="text-center" height="750px">
                                                <div id="printMe">
                                                    <v-container>
                                                        <v-row justify="center">
                                                            <v-col justify="center" cols="20">
                                                                <v-form v-model="valid" ref="form" >
                                                                    <!-- SOURCE -->
                                                                    <h1>แบบสำหรับส่งผู้ป่วยไปรับการตรวจหรือรักษาต่อ</h1> <br>
                                                                    <v-row>
                                                                        <v-col cols="10">
                                                                            <p class="text-left"><strong>ชื่อผู้ป่วย: {{this.patientName}}</strong> </p>
                                                                        </v-col>
                                                                        <v-col cols="10">
                                                                            <p class="text-left" ><strong>รหัสบัตรประชาชน : {{this.discharge.nationalId}}</strong></p>
                                                                        </v-col>
                                                                    </v-row>
                                                                    <v-row>
                                                                        <v-col cols="10">
                                                                            <p class="text-left"><strong>ระดับความรู้สึกตัว : {{sensoriumName}} </strong> </p>
                                                                        </v-col>
                                                                        <v-col cols="10">
                                                                            <p class="text-left"><strong>ความสามารถในการดูแลตนเอง : {{selfcareName}} </strong> </p>
                                                                        </v-col>
                                                                    </v-row>
                                                                    <v-row >
                                                                        <v-col cols="10">
                                                                            <p class="text-left"><strong>ประเภทการนำจำหน่าย : {{distributionTypeName}} </strong> </p>
                                                                        </v-col>
                                                                    </v-row>
                                                                    
                                                                    <v-row>
                                                                        <v-col cols="10">
                                                                            <p class="text-left"><strong>หมายเหตุ : {{this.discharge.note}}</strong> </p>
                                                                        </v-col>
                                                                    </v-row>


                                                                </v-form>
                                                            </v-col>
                                                        </v-row>
                                                    </v-container>
                                                </div>

                                            </v-sheet>
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
        name: "discharge",
        components: {
            // eslint-disable-next-line vue/no-unused-components
            DatePicker
        },
        data() {
            return {
                discharge: {
                    nationalId: '',
                    sensoriumId: '',
                    selfcareId: '',
                    distributionTypeId: '',
                    note: ''
                },
                sheet: false,
                valid: false,
                patientCheck: false,
                patientName: '',
                sensoriumName: '',
                selfcareName: '',
                distributionTypeName: '',
                note: ''
                
            };
        },
        methods: {
            print() {
                this.$htmlToPaper('printMe');
            },
            /* eslint-disable no-console */
            getSensorium() {
                http
                    .get("/sensorium")
                    .then(response => {
                        this.sensorium = response.data;
                        console.log(response.data);
                    })
                    .catch(e => {
                        console.log(e);
                    });
            },

            getSelfcare() {
                http
                    .get("/selfcare")
                    .then(response => {
                        this.selfcare = response.data;
                        console.log(response.data);
                    })
                    .catch(e => {
                        console.log(e);
                    });
            },

            getDistributionType() {
                http
                    .get("/distributionType")
                    .then(response => {
                        this.distributionType = response.data;
                        console.log(response.data);
                    })
                    .catch(e => {
                        console.log(e);
                    });
            },
            /* eslint-disable */
            findPatient() {
                http
                    .get("/patient/"+this.discharge.nationalId)
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
            findSensorium() {
                http
                    .get("/sensorium/"+this.discharge.sensoriumId)
                    .then(response => {
                        console.log(response);
                        this.sensoriumName = response.data.name;
                        console.log(sensorium);
                    })
                    .catch(e => {
                        console.log(e);
                    });
                
            },
            findSelfcare() {
                http
                    .get("/selfcare/"+this.discharge.selfcareId)
                    .then(response => {
                        console.log(response);
                        this.selfcareName = response.data.name;
                        //this.findDistributionType();
                        console.log(selfcare);
                    })
                    .catch(e => {
                        console.log(e);
                    });
                
            },
            findDistributionType() {
                http
                    .get("/distributionType/"+this.discharge.distributionTypeId)
                    .then(response => {
                        console.log(response);
                        this.distributionTypeName = response.data.name;
                        
                        console.log(distributionType);
                    })
                    .catch(e => {
                        console.log(e);
                    });
                
            },

            saveDischarge() {
                http
                    .post("/adddischarge/"+
                        this.discharge.nationalId+
                        "/"+this.discharge.sensoriumId+
                        "/"+this.discharge.selfcareId+
                        "/"+this.discharge.distributionTypeId+
                        "/"+this.discharge.note+
                        "/",this.discharge)
                    .then(response => {
                        console.log(response);
                        const options1 = { title: "Alert", size: "sm" };
                        this.$dialogs.alert("บันทึกข้อมูลสำเร็จ", options1);
                        this.findSelfcare();
                        this.findSensorium();
                        this.findDistributionType();
                    })
                    .catch(e => {
                        console.log(e);
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
                this.getSensorium();
                this.getSelfcare();
                this.getDistributionType();
                this.findSensorium();
                this.findSelfcare();
                this.findDistributionType();
            }
            /* eslint-enable no-console */
        },
        mounted() {
            this.getSensorium();
            this.getSelfcare();
            this.getDistributionType();
            this.findSensorium();
            this.findSelfcare();
            this.findDistributionType();

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