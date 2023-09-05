namespace AudAerodrom {
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
            this.lbAirports = new System.Windows.Forms.ListBox();
            this.lbDestinations = new System.Windows.Forms.ListBox();
            this.btnAddAirport = new System.Windows.Forms.Button();
            this.btnDeleteAirport = new System.Windows.Forms.Button();
            this.btnAddDestination = new System.Windows.Forms.Button();
            this.tbMostExpensiveDestination = new System.Windows.Forms.TextBox();
            this.tbAverageDestination = new System.Windows.Forms.TextBox();
            this.lbe1 = new System.Windows.Forms.Label();
            this.label1 = new System.Windows.Forms.Label();
            this.SuspendLayout();
            // 
            // lbAirports
            // 
            this.lbAirports.FormattingEnabled = true;
            this.lbAirports.Location = new System.Drawing.Point(12, 12);
            this.lbAirports.Name = "lbAirports";
            this.lbAirports.Size = new System.Drawing.Size(353, 264);
            this.lbAirports.TabIndex = 0;
            this.lbAirports.SelectedIndexChanged += new System.EventHandler(this.lbAirports_SelectedIndexChanged);
            // 
            // lbDestinations
            // 
            this.lbDestinations.FormattingEnabled = true;
            this.lbDestinations.Location = new System.Drawing.Point(371, 12);
            this.lbDestinations.Name = "lbDestinations";
            this.lbDestinations.Size = new System.Drawing.Size(417, 264);
            this.lbDestinations.TabIndex = 1;
            // 
            // btnAddAirport
            // 
            this.btnAddAirport.Location = new System.Drawing.Point(13, 313);
            this.btnAddAirport.Name = "btnAddAirport";
            this.btnAddAirport.Size = new System.Drawing.Size(352, 23);
            this.btnAddAirport.TabIndex = 2;
            this.btnAddAirport.Text = "Dodadi Aerodrom";
            this.btnAddAirport.UseVisualStyleBackColor = true;
            this.btnAddAirport.Click += new System.EventHandler(this.btnAddAirport_Click);
            // 
            // btnDeleteAirport
            // 
            this.btnDeleteAirport.Location = new System.Drawing.Point(13, 342);
            this.btnDeleteAirport.Name = "btnDeleteAirport";
            this.btnDeleteAirport.Size = new System.Drawing.Size(352, 23);
            this.btnDeleteAirport.TabIndex = 3;
            this.btnDeleteAirport.Text = "Izbrisi Aerodrom";
            this.btnDeleteAirport.UseVisualStyleBackColor = true;
            this.btnDeleteAirport.Click += new System.EventHandler(this.btnDeleteAirport_Click);
            // 
            // btnAddDestination
            // 
            this.btnAddDestination.Location = new System.Drawing.Point(12, 369);
            this.btnAddDestination.Name = "btnAddDestination";
            this.btnAddDestination.Size = new System.Drawing.Size(352, 23);
            this.btnAddDestination.TabIndex = 4;
            this.btnAddDestination.Text = "Dodadi destinacija";
            this.btnAddDestination.UseVisualStyleBackColor = true;
            this.btnAddDestination.Click += new System.EventHandler(this.btnAddDestination_Click);
            // 
            // tbMostExpensiveDestination
            // 
            this.tbMostExpensiveDestination.Enabled = false;
            this.tbMostExpensiveDestination.Location = new System.Drawing.Point(371, 315);
            this.tbMostExpensiveDestination.Name = "tbMostExpensiveDestination";
            this.tbMostExpensiveDestination.Size = new System.Drawing.Size(417, 20);
            this.tbMostExpensiveDestination.TabIndex = 5;
            // 
            // tbAverageDestination
            // 
            this.tbAverageDestination.Enabled = false;
            this.tbAverageDestination.Location = new System.Drawing.Point(371, 369);
            this.tbAverageDestination.Name = "tbAverageDestination";
            this.tbAverageDestination.Size = new System.Drawing.Size(417, 20);
            this.tbAverageDestination.TabIndex = 6;
            // 
            // lbe1
            // 
            this.lbe1.AutoSize = true;
            this.lbe1.Location = new System.Drawing.Point(372, 296);
            this.lbe1.Name = "lbe1";
            this.lbe1.Size = new System.Drawing.Size(108, 13);
            this.lbe1.TabIndex = 7;
            this.lbe1.Text = "Najskapa destinacija ";
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(372, 354);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(90, 13);
            this.label1.TabIndex = 8;
            this.label1.Text = "Prosecna Dolzina";
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 450);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.lbe1);
            this.Controls.Add(this.tbAverageDestination);
            this.Controls.Add(this.tbMostExpensiveDestination);
            this.Controls.Add(this.btnAddDestination);
            this.Controls.Add(this.btnDeleteAirport);
            this.Controls.Add(this.btnAddAirport);
            this.Controls.Add(this.lbDestinations);
            this.Controls.Add(this.lbAirports);
            this.Name = "Form1";
            this.Text = "Form1";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.ListBox lbAirports;
        private System.Windows.Forms.ListBox lbDestinations;
        private System.Windows.Forms.Button btnAddAirport;
        private System.Windows.Forms.Button btnDeleteAirport;
        private System.Windows.Forms.Button btnAddDestination;
        private System.Windows.Forms.TextBox tbMostExpensiveDestination;
        private System.Windows.Forms.TextBox tbAverageDestination;
        private System.Windows.Forms.Label lbe1;
        private System.Windows.Forms.Label label1;
    }
}

