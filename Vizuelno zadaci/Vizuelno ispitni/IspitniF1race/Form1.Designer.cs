namespace IspitniF1race {
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
            this.lbDrivers = new System.Windows.Forms.ListBox();
            this.lbLaps = new System.Windows.Forms.ListBox();
            this.btnAddDriver = new System.Windows.Forms.Button();
            this.btnRemoveDriver = new System.Windows.Forms.Button();
            this.btnAddLap = new System.Windows.Forms.Button();
            this.nudMinutes = new System.Windows.Forms.NumericUpDown();
            this.nudSeconds = new System.Windows.Forms.NumericUpDown();
            this.tbBestLap = new System.Windows.Forms.TextBox();
            this.nudTime = new System.Windows.Forms.NumericUpDown();
            ((System.ComponentModel.ISupportInitialize)(this.nudMinutes)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.nudSeconds)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.nudTime)).BeginInit();
            this.SuspendLayout();
            // 
            // lbDrivers
            // 
            this.lbDrivers.FormattingEnabled = true;
            this.lbDrivers.Location = new System.Drawing.Point(13, 13);
            this.lbDrivers.Name = "lbDrivers";
            this.lbDrivers.Size = new System.Drawing.Size(330, 316);
            this.lbDrivers.TabIndex = 0;
            this.lbDrivers.SelectedIndexChanged += new System.EventHandler(this.lbDrivers_SelectedIndexChanged);
            // 
            // lbLaps
            // 
            this.lbLaps.FormattingEnabled = true;
            this.lbLaps.Location = new System.Drawing.Point(458, 12);
            this.lbLaps.Name = "lbLaps";
            this.lbLaps.Size = new System.Drawing.Size(330, 316);
            this.lbLaps.TabIndex = 1;
            // 
            // btnAddDriver
            // 
            this.btnAddDriver.Location = new System.Drawing.Point(13, 336);
            this.btnAddDriver.Name = "btnAddDriver";
            this.btnAddDriver.Size = new System.Drawing.Size(330, 23);
            this.btnAddDriver.TabIndex = 2;
            this.btnAddDriver.Text = "button1";
            this.btnAddDriver.UseVisualStyleBackColor = true;
            this.btnAddDriver.Click += new System.EventHandler(this.btnAddDriver_Click);
            // 
            // btnRemoveDriver
            // 
            this.btnRemoveDriver.Location = new System.Drawing.Point(12, 365);
            this.btnRemoveDriver.Name = "btnRemoveDriver";
            this.btnRemoveDriver.Size = new System.Drawing.Size(330, 23);
            this.btnRemoveDriver.TabIndex = 3;
            this.btnRemoveDriver.Text = "Izbrisi";
            this.btnRemoveDriver.UseVisualStyleBackColor = true;
            this.btnRemoveDriver.Click += new System.EventHandler(this.btnRemoveDriver_Click);
            // 
            // btnAddLap
            // 
            this.btnAddLap.Location = new System.Drawing.Point(705, 348);
            this.btnAddLap.Name = "btnAddLap";
            this.btnAddLap.Size = new System.Drawing.Size(83, 23);
            this.btnAddLap.TabIndex = 4;
            this.btnAddLap.Text = "button1";
            this.btnAddLap.UseVisualStyleBackColor = true;
            this.btnAddLap.Click += new System.EventHandler(this.btnAddLap_Click);
            // 
            // nudMinutes
            // 
            this.nudMinutes.Location = new System.Drawing.Point(507, 348);
            this.nudMinutes.Maximum = new decimal(new int[] {
            60,
            0,
            0,
            0});
            this.nudMinutes.Name = "nudMinutes";
            this.nudMinutes.Size = new System.Drawing.Size(93, 20);
            this.nudMinutes.TabIndex = 5;
            this.nudMinutes.ValueChanged += new System.EventHandler(this.nudMinutes_ValueChanged);
            this.nudMinutes.MouseDown += new System.Windows.Forms.MouseEventHandler(this.nudMinutes_MouseDown);
            // 
            // nudSeconds
            // 
            this.nudSeconds.Location = new System.Drawing.Point(606, 348);
            this.nudSeconds.Maximum = new decimal(new int[] {
            60,
            0,
            0,
            0});
            this.nudSeconds.Name = "nudSeconds";
            this.nudSeconds.Size = new System.Drawing.Size(93, 20);
            this.nudSeconds.TabIndex = 6;
            this.nudSeconds.ValueChanged += new System.EventHandler(this.nudSeconds_ValueChanged);
            // 
            // tbBestLap
            // 
            this.tbBestLap.Enabled = false;
            this.tbBestLap.Location = new System.Drawing.Point(458, 385);
            this.tbBestLap.Name = "tbBestLap";
            this.tbBestLap.Size = new System.Drawing.Size(330, 20);
            this.tbBestLap.TabIndex = 7;
            // 
            // nudTime
            // 
            this.nudTime.Location = new System.Drawing.Point(458, 418);
            this.nudTime.Maximum = new decimal(new int[] {
            60,
            0,
            0,
            0});
            this.nudTime.Name = "nudTime";
            this.nudTime.Size = new System.Drawing.Size(93, 20);
            this.nudTime.TabIndex = 8;
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 450);
            this.Controls.Add(this.nudTime);
            this.Controls.Add(this.tbBestLap);
            this.Controls.Add(this.nudSeconds);
            this.Controls.Add(this.nudMinutes);
            this.Controls.Add(this.btnAddLap);
            this.Controls.Add(this.btnRemoveDriver);
            this.Controls.Add(this.btnAddDriver);
            this.Controls.Add(this.lbLaps);
            this.Controls.Add(this.lbDrivers);
            this.Name = "Form1";
            this.Text = "Form1";
            ((System.ComponentModel.ISupportInitialize)(this.nudMinutes)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.nudSeconds)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.nudTime)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.ListBox lbDrivers;
        private System.Windows.Forms.ListBox lbLaps;
        private System.Windows.Forms.Button btnAddDriver;
        private System.Windows.Forms.Button btnRemoveDriver;
        private System.Windows.Forms.Button btnAddLap;
        private System.Windows.Forms.NumericUpDown nudMinutes;
        private System.Windows.Forms.NumericUpDown nudSeconds;
        private System.Windows.Forms.TextBox tbBestLap;
        private System.Windows.Forms.NumericUpDown nudTime;
    }
}

