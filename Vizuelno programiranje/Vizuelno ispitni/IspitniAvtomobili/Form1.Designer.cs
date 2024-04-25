namespace IspitniAvtomobili {
    partial class Form1 {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing) {
            if( disposing && ( components != null ) ) {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent() {
            this.groupBox1 = new System.Windows.Forms.GroupBox();
            this.groupBox2 = new System.Windows.Forms.GroupBox();
            this.groupBox3 = new System.Windows.Forms.GroupBox();
            this.cbMarka = new System.Windows.Forms.ComboBox();
            this.label1 = new System.Windows.Forms.Label();
            this.tbModel = new System.Windows.Forms.TextBox();
            this.label2 = new System.Windows.Forms.Label();
            this.nudFuel = new System.Windows.Forms.NumericUpDown();
            this.nudPrice = new System.Windows.Forms.NumericUpDown();
            this.label3 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.lbMakes = new System.Windows.Forms.ListBox();
            this.lbCars = new System.Windows.Forms.ListBox();
            this.tbAvgFuel = new System.Windows.Forms.TextBox();
            this.tbMostEfficient = new System.Windows.Forms.TextBox();
            this.tbMostExpensive = new System.Windows.Forms.TextBox();
            this.label5 = new System.Windows.Forms.Label();
            this.label6 = new System.Windows.Forms.Label();
            this.label7 = new System.Windows.Forms.Label();
            this.btnAddCar = new System.Windows.Forms.Button();
            this.btnDelCar = new System.Windows.Forms.Button();
            this.btnAddMake = new System.Windows.Forms.Button();
            this.groupBox1.SuspendLayout();
            this.groupBox2.SuspendLayout();
            this.groupBox3.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.nudFuel)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.nudPrice)).BeginInit();
            this.SuspendLayout();
            // 
            // groupBox1
            // 
            this.groupBox1.Controls.Add(this.btnDelCar);
            this.groupBox1.Controls.Add(this.btnAddCar);
            this.groupBox1.Controls.Add(this.label4);
            this.groupBox1.Controls.Add(this.label3);
            this.groupBox1.Controls.Add(this.nudPrice);
            this.groupBox1.Controls.Add(this.nudFuel);
            this.groupBox1.Controls.Add(this.label2);
            this.groupBox1.Controls.Add(this.tbModel);
            this.groupBox1.Controls.Add(this.label1);
            this.groupBox1.Controls.Add(this.cbMarka);
            this.groupBox1.Location = new System.Drawing.Point(12, 12);
            this.groupBox1.Name = "groupBox1";
            this.groupBox1.Size = new System.Drawing.Size(342, 203);
            this.groupBox1.TabIndex = 0;
            this.groupBox1.TabStop = false;
            this.groupBox1.Text = "Нов автомобил:";
            // 
            // groupBox2
            // 
            this.groupBox2.Controls.Add(this.lbMakes);
            this.groupBox2.Location = new System.Drawing.Point(12, 221);
            this.groupBox2.Name = "groupBox2";
            this.groupBox2.Size = new System.Drawing.Size(342, 203);
            this.groupBox2.TabIndex = 1;
            this.groupBox2.TabStop = false;
            this.groupBox2.Text = "Марки на автомобили";
            // 
            // groupBox3
            // 
            this.groupBox3.Controls.Add(this.label7);
            this.groupBox3.Controls.Add(this.label6);
            this.groupBox3.Controls.Add(this.label5);
            this.groupBox3.Controls.Add(this.tbMostExpensive);
            this.groupBox3.Controls.Add(this.tbMostEfficient);
            this.groupBox3.Controls.Add(this.tbAvgFuel);
            this.groupBox3.Controls.Add(this.lbCars);
            this.groupBox3.Location = new System.Drawing.Point(360, 12);
            this.groupBox3.Name = "groupBox3";
            this.groupBox3.Size = new System.Drawing.Size(428, 426);
            this.groupBox3.TabIndex = 2;
            this.groupBox3.TabStop = false;
            this.groupBox3.Text = "Листа на автомобили";
            // 
            // cbMarka
            // 
            this.cbMarka.FormattingEnabled = true;
            this.cbMarka.Location = new System.Drawing.Point(7, 34);
            this.cbMarka.Name = "cbMarka";
            this.cbMarka.Size = new System.Drawing.Size(329, 21);
            this.cbMarka.TabIndex = 0;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(7, 15);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(40, 13);
            this.label1.TabIndex = 1;
            this.label1.Text = "Марка";
            // 
            // tbModel
            // 
            this.tbModel.Location = new System.Drawing.Point(6, 78);
            this.tbModel.Name = "tbModel";
            this.tbModel.Size = new System.Drawing.Size(330, 20);
            this.tbModel.TabIndex = 2;
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(10, 62);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(36, 13);
            this.label2.TabIndex = 3;
            this.label2.Text = "Model";
            // 
            // nudFuel
            // 
            this.nudFuel.DecimalPlaces = 1;
            this.nudFuel.Increment = new decimal(new int[] {
            25,
            0,
            0,
            131072});
            this.nudFuel.Location = new System.Drawing.Point(7, 121);
            this.nudFuel.Minimum = new decimal(new int[] {
            1,
            0,
            0,
            0});
            this.nudFuel.Name = "nudFuel";
            this.nudFuel.Size = new System.Drawing.Size(120, 20);
            this.nudFuel.TabIndex = 4;
            this.nudFuel.Value = new decimal(new int[] {
            1,
            0,
            0,
            0});
            // 
            // nudPrice
            // 
            this.nudPrice.Increment = new decimal(new int[] {
            100,
            0,
            0,
            0});
            this.nudPrice.Location = new System.Drawing.Point(6, 160);
            this.nudPrice.Maximum = new decimal(new int[] {
            1000000,
            0,
            0,
            0});
            this.nudPrice.Name = "nudPrice";
            this.nudPrice.Size = new System.Drawing.Size(120, 20);
            this.nudPrice.TabIndex = 5;
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(11, 105);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(80, 13);
            this.label3.TabIndex = 6;
            this.label3.Text = "Потрошувачка";
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(11, 144);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(33, 13);
            this.label4.TabIndex = 7;
            this.label4.Text = "Цена";
            // 
            // lbMakes
            // 
            this.lbMakes.FormattingEnabled = true;
            this.lbMakes.Location = new System.Drawing.Point(10, 20);
            this.lbMakes.Name = "lbMakes";
            this.lbMakes.Size = new System.Drawing.Size(326, 173);
            this.lbMakes.TabIndex = 0;
            this.lbMakes.SelectedIndexChanged += new System.EventHandler(this.lbMakes_SelectedIndexChanged);
            // 
            // lbCars
            // 
            this.lbCars.FormattingEnabled = true;
            this.lbCars.Location = new System.Drawing.Point(7, 34);
            this.lbCars.Name = "lbCars";
            this.lbCars.Size = new System.Drawing.Size(415, 199);
            this.lbCars.TabIndex = 0;
            // 
            // tbAvgFuel
            // 
            this.tbAvgFuel.Enabled = false;
            this.tbAvgFuel.Location = new System.Drawing.Point(154, 270);
            this.tbAvgFuel.Name = "tbAvgFuel";
            this.tbAvgFuel.Size = new System.Drawing.Size(268, 20);
            this.tbAvgFuel.TabIndex = 8;
            // 
            // tbMostEfficient
            // 
            this.tbMostEfficient.Enabled = false;
            this.tbMostEfficient.Location = new System.Drawing.Point(6, 345);
            this.tbMostEfficient.Name = "tbMostEfficient";
            this.tbMostEfficient.Size = new System.Drawing.Size(415, 20);
            this.tbMostEfficient.TabIndex = 9;
            // 
            // tbMostExpensive
            // 
            this.tbMostExpensive.Enabled = false;
            this.tbMostExpensive.Location = new System.Drawing.Point(6, 392);
            this.tbMostExpensive.Name = "tbMostExpensive";
            this.tbMostExpensive.Size = new System.Drawing.Size(415, 20);
            this.tbMostExpensive.TabIndex = 10;
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(18, 273);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(130, 13);
            this.label5.TabIndex = 8;
            this.label5.Text = "Просечна потрошувачка";
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Location = new System.Drawing.Point(6, 329);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(84, 13);
            this.label6.TabIndex = 11;
            this.label6.Text = "Најекономичен";
            // 
            // label7
            // 
            this.label7.AutoSize = true;
            this.label7.Location = new System.Drawing.Point(6, 376);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(47, 13);
            this.label7.TabIndex = 12;
            this.label7.Text = "Најскап";
            // 
            // btnAddCar
            // 
            this.btnAddCar.Location = new System.Drawing.Point(153, 156);
            this.btnAddCar.Name = "btnAddCar";
            this.btnAddCar.Size = new System.Drawing.Size(75, 23);
            this.btnAddCar.TabIndex = 8;
            this.btnAddCar.Text = "Додади";
            this.btnAddCar.UseVisualStyleBackColor = true;
            this.btnAddCar.Click += new System.EventHandler(this.btnAddCar_Click);
            // 
            // btnDelCar
            // 
            this.btnDelCar.Location = new System.Drawing.Point(261, 156);
            this.btnDelCar.Name = "btnDelCar";
            this.btnDelCar.Size = new System.Drawing.Size(75, 23);
            this.btnDelCar.TabIndex = 10;
            this.btnDelCar.Text = "Избриши";
            this.btnDelCar.UseVisualStyleBackColor = true;
            this.btnDelCar.Click += new System.EventHandler(this.btnDelCar_Click);
            // 
            // btnAddMake
            // 
            this.btnAddMake.Location = new System.Drawing.Point(200, 420);
            this.btnAddMake.Name = "btnAddMake";
            this.btnAddMake.Size = new System.Drawing.Size(75, 23);
            this.btnAddMake.TabIndex = 3;
            this.btnAddMake.Text = "button1";
            this.btnAddMake.UseVisualStyleBackColor = true;
            this.btnAddMake.Click += new System.EventHandler(this.btnAddMake_Click);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 450);
            this.Controls.Add(this.btnAddMake);
            this.Controls.Add(this.groupBox3);
            this.Controls.Add(this.groupBox2);
            this.Controls.Add(this.groupBox1);
            this.Name = "Form1";
            this.Text = "Form1";
            this.groupBox1.ResumeLayout(false);
            this.groupBox1.PerformLayout();
            this.groupBox2.ResumeLayout(false);
            this.groupBox3.ResumeLayout(false);
            this.groupBox3.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.nudFuel)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.nudPrice)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.GroupBox groupBox1;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.NumericUpDown nudPrice;
        private System.Windows.Forms.NumericUpDown nudFuel;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.TextBox tbModel;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.ComboBox cbMarka;
        private System.Windows.Forms.GroupBox groupBox2;
        private System.Windows.Forms.ListBox lbMakes;
        private System.Windows.Forms.GroupBox groupBox3;
        private System.Windows.Forms.TextBox tbMostExpensive;
        private System.Windows.Forms.TextBox tbMostEfficient;
        private System.Windows.Forms.TextBox tbAvgFuel;
        private System.Windows.Forms.ListBox lbCars;
        private System.Windows.Forms.Button btnAddCar;
        private System.Windows.Forms.Label label7;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.Button btnDelCar;
        private System.Windows.Forms.Button btnAddMake;
    }
}

