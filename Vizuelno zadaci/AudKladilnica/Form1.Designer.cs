namespace AudKladilnica {
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
            this.components = new System.ComponentModel.Container();
            this.lbTeams = new System.Windows.Forms.ListBox();
            this.lbGames = new System.Windows.Forms.ListBox();
            this.lbTicket = new System.Windows.Forms.ListBox();
            this.gbKreirajBilten = new System.Windows.Forms.GroupBox();
            this.mtbCode = new System.Windows.Forms.MaskedTextBox();
            this.btnDodadiNovTim = new System.Windows.Forms.Button();
            this.btnDodadiVoBilten = new System.Windows.Forms.Button();
            this.label3 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label1 = new System.Windows.Forms.Label();
            this.nud2 = new System.Windows.Forms.NumericUpDown();
            this.nudX = new System.Windows.Forms.NumericUpDown();
            this.nud1 = new System.Windows.Forms.NumericUpDown();
            this.kbDodadiNatprevar = new System.Windows.Forms.GroupBox();
            this.btnAddGane = new System.Windows.Forms.Button();
            this.cbTim = new System.Windows.Forms.ComboBox();
            this.tbShifraNatprevar = new System.Windows.Forms.TextBox();
            this.gbUplati = new System.Windows.Forms.GroupBox();
            this.nudUplata = new System.Windows.Forms.NumericUpDown();
            this.btnPrint = new System.Windows.Forms.Button();
            this.tbDobivka = new System.Windows.Forms.TextBox();
            this.tbKoeficient = new System.Windows.Forms.TextBox();
            this.contextMenuStrip1 = new System.Windows.Forms.ContextMenuStrip(this.components);
            this.gbKreirajBilten.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.nud2)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.nudX)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.nud1)).BeginInit();
            this.kbDodadiNatprevar.SuspendLayout();
            this.gbUplati.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.nudUplata)).BeginInit();
            this.SuspendLayout();
            // 
            // lbTeams
            // 
            this.lbTeams.FormattingEnabled = true;
            this.lbTeams.Location = new System.Drawing.Point(10, 12);
            this.lbTeams.Name = "lbTeams";
            this.lbTeams.SelectionMode = System.Windows.Forms.SelectionMode.MultiSimple;
            this.lbTeams.Size = new System.Drawing.Size(207, 290);
            this.lbTeams.TabIndex = 0;
            // 
            // lbGames
            // 
            this.lbGames.FormattingEnabled = true;
            this.lbGames.Location = new System.Drawing.Point(223, 12);
            this.lbGames.Name = "lbGames";
            this.lbGames.Size = new System.Drawing.Size(207, 290);
            this.lbGames.TabIndex = 1;
            // 
            // lbTicket
            // 
            this.lbTicket.FormattingEnabled = true;
            this.lbTicket.Location = new System.Drawing.Point(436, 12);
            this.lbTicket.Name = "lbTicket";
            this.lbTicket.Size = new System.Drawing.Size(207, 290);
            this.lbTicket.TabIndex = 2;
            // 
            // gbKreirajBilten
            // 
            this.gbKreirajBilten.Controls.Add(this.mtbCode);
            this.gbKreirajBilten.Controls.Add(this.btnDodadiNovTim);
            this.gbKreirajBilten.Controls.Add(this.btnDodadiVoBilten);
            this.gbKreirajBilten.Controls.Add(this.label3);
            this.gbKreirajBilten.Controls.Add(this.label2);
            this.gbKreirajBilten.Controls.Add(this.label1);
            this.gbKreirajBilten.Controls.Add(this.nud2);
            this.gbKreirajBilten.Controls.Add(this.nudX);
            this.gbKreirajBilten.Controls.Add(this.nud1);
            this.gbKreirajBilten.Location = new System.Drawing.Point(10, 308);
            this.gbKreirajBilten.Name = "gbKreirajBilten";
            this.gbKreirajBilten.Size = new System.Drawing.Size(207, 220);
            this.gbKreirajBilten.TabIndex = 3;
            this.gbKreirajBilten.TabStop = false;
            this.gbKreirajBilten.Text = "Креирај блтен";
            // 
            // mtbCode
            // 
            this.mtbCode.Location = new System.Drawing.Point(72, 115);
            this.mtbCode.Mask = "0000";
            this.mtbCode.Name = "mtbCode";
            this.mtbCode.Size = new System.Drawing.Size(100, 20);
            this.mtbCode.TabIndex = 9;
            // 
            // btnDodadiNovTim
            // 
            this.btnDodadiNovTim.Location = new System.Drawing.Point(37, 184);
            this.btnDodadiNovTim.Name = "btnDodadiNovTim";
            this.btnDodadiNovTim.Size = new System.Drawing.Size(75, 23);
            this.btnDodadiNovTim.TabIndex = 8;
            this.btnDodadiNovTim.Text = "novtim";
            this.btnDodadiNovTim.UseVisualStyleBackColor = true;
            this.btnDodadiNovTim.Click += new System.EventHandler(this.btnDodadiNovTim_Click);
            // 
            // btnDodadiVoBilten
            // 
            this.btnDodadiVoBilten.Location = new System.Drawing.Point(37, 155);
            this.btnDodadiVoBilten.Name = "btnDodadiVoBilten";
            this.btnDodadiVoBilten.Size = new System.Drawing.Size(75, 23);
            this.btnDodadiVoBilten.TabIndex = 7;
            this.btnDodadiVoBilten.Text = "vobilten";
            this.btnDodadiVoBilten.UseVisualStyleBackColor = true;
            this.btnDodadiVoBilten.Click += new System.EventHandler(this.btnDodadiVoBilten_Click);
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(2, 82);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(13, 13);
            this.label3.TabIndex = 5;
            this.label3.Text = "2";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(2, 56);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(12, 13);
            this.label2.TabIndex = 4;
            this.label2.Text = "x";
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(2, 30);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(13, 13);
            this.label1.TabIndex = 3;
            this.label1.Text = "1";
            // 
            // nud2
            // 
            this.nud2.DecimalPlaces = 2;
            this.nud2.Increment = new decimal(new int[] {
            25,
            0,
            0,
            131072});
            this.nud2.Location = new System.Drawing.Point(17, 80);
            this.nud2.Minimum = new decimal(new int[] {
            1,
            0,
            0,
            0});
            this.nud2.Name = "nud2";
            this.nud2.Size = new System.Drawing.Size(120, 20);
            this.nud2.TabIndex = 2;
            this.nud2.Value = new decimal(new int[] {
            1,
            0,
            0,
            0});
            // 
            // nudX
            // 
            this.nudX.DecimalPlaces = 2;
            this.nudX.Increment = new decimal(new int[] {
            25,
            0,
            0,
            131072});
            this.nudX.Location = new System.Drawing.Point(17, 54);
            this.nudX.Minimum = new decimal(new int[] {
            1,
            0,
            0,
            0});
            this.nudX.Name = "nudX";
            this.nudX.Size = new System.Drawing.Size(120, 20);
            this.nudX.TabIndex = 1;
            this.nudX.Value = new decimal(new int[] {
            1,
            0,
            0,
            0});
            // 
            // nud1
            // 
            this.nud1.DecimalPlaces = 2;
            this.nud1.Increment = new decimal(new int[] {
            25,
            0,
            0,
            131072});
            this.nud1.Location = new System.Drawing.Point(17, 28);
            this.nud1.Minimum = new decimal(new int[] {
            1,
            0,
            0,
            0});
            this.nud1.Name = "nud1";
            this.nud1.Size = new System.Drawing.Size(120, 20);
            this.nud1.TabIndex = 0;
            this.nud1.Value = new decimal(new int[] {
            1,
            0,
            0,
            0});
            // 
            // kbDodadiNatprevar
            // 
            this.kbDodadiNatprevar.Controls.Add(this.btnAddGane);
            this.kbDodadiNatprevar.Controls.Add(this.cbTim);
            this.kbDodadiNatprevar.Controls.Add(this.tbShifraNatprevar);
            this.kbDodadiNatprevar.Location = new System.Drawing.Point(223, 308);
            this.kbDodadiNatprevar.Name = "kbDodadiNatprevar";
            this.kbDodadiNatprevar.Size = new System.Drawing.Size(207, 220);
            this.kbDodadiNatprevar.TabIndex = 4;
            this.kbDodadiNatprevar.TabStop = false;
            this.kbDodadiNatprevar.Text = "додади натпревар во тикет";
            // 
            // btnAddGane
            // 
            this.btnAddGane.Location = new System.Drawing.Point(53, 184);
            this.btnAddGane.Name = "btnAddGane";
            this.btnAddGane.Size = new System.Drawing.Size(75, 23);
            this.btnAddGane.TabIndex = 12;
            this.btnAddGane.Text = "pecati";
            this.btnAddGane.UseVisualStyleBackColor = true;
            this.btnAddGane.Click += new System.EventHandler(this.btnAddGane_Click);
            // 
            // cbTim
            // 
            this.cbTim.FormattingEnabled = true;
            this.cbTim.Items.AddRange(new object[] {
            "1",
            "x",
            "2"});
            this.cbTim.Location = new System.Drawing.Point(53, 155);
            this.cbTim.Name = "cbTim";
            this.cbTim.Size = new System.Drawing.Size(121, 21);
            this.cbTim.TabIndex = 10;
            // 
            // tbShifraNatprevar
            // 
            this.tbShifraNatprevar.Location = new System.Drawing.Point(53, 115);
            this.tbShifraNatprevar.Name = "tbShifraNatprevar";
            this.tbShifraNatprevar.Size = new System.Drawing.Size(100, 20);
            this.tbShifraNatprevar.TabIndex = 9;
            this.tbShifraNatprevar.TextChanged += new System.EventHandler(this.tbShifraNatprevar_TextChanged);
            // 
            // gbUplati
            // 
            this.gbUplati.Controls.Add(this.nudUplata);
            this.gbUplati.Controls.Add(this.btnPrint);
            this.gbUplati.Controls.Add(this.tbDobivka);
            this.gbUplati.Controls.Add(this.tbKoeficient);
            this.gbUplati.Location = new System.Drawing.Point(436, 308);
            this.gbUplati.Name = "gbUplati";
            this.gbUplati.Size = new System.Drawing.Size(207, 220);
            this.gbUplati.TabIndex = 4;
            this.gbUplati.TabStop = false;
            this.gbUplati.Text = "уплати тикет";
            // 
            // nudUplata
            // 
            this.nudUplata.Increment = new decimal(new int[] {
            25,
            0,
            0,
            0});
            this.nudUplata.Location = new System.Drawing.Point(15, 115);
            this.nudUplata.Name = "nudUplata";
            this.nudUplata.Size = new System.Drawing.Size(120, 20);
            this.nudUplata.TabIndex = 12;
            this.nudUplata.ValueChanged += new System.EventHandler(this.nudUplata_ValueChanged);
            // 
            // btnPrint
            // 
            this.btnPrint.Location = new System.Drawing.Point(15, 184);
            this.btnPrint.Name = "btnPrint";
            this.btnPrint.Size = new System.Drawing.Size(75, 23);
            this.btnPrint.TabIndex = 9;
            this.btnPrint.Text = "pecati";
            this.btnPrint.UseVisualStyleBackColor = true;
            // 
            // tbDobivka
            // 
            this.tbDobivka.Enabled = false;
            this.tbDobivka.Location = new System.Drawing.Point(15, 158);
            this.tbDobivka.Name = "tbDobivka";
            this.tbDobivka.Size = new System.Drawing.Size(100, 20);
            this.tbDobivka.TabIndex = 11;
            // 
            // tbKoeficient
            // 
            this.tbKoeficient.Enabled = false;
            this.tbKoeficient.Location = new System.Drawing.Point(15, 49);
            this.tbKoeficient.Name = "tbKoeficient";
            this.tbKoeficient.Size = new System.Drawing.Size(100, 20);
            this.tbKoeficient.TabIndex = 10;
            // 
            // contextMenuStrip1
            // 
            this.contextMenuStrip1.Name = "contextMenuStrip1";
            this.contextMenuStrip1.Size = new System.Drawing.Size(61, 4);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(655, 537);
            this.Controls.Add(this.gbUplati);
            this.Controls.Add(this.kbDodadiNatprevar);
            this.Controls.Add(this.gbKreirajBilten);
            this.Controls.Add(this.lbTicket);
            this.Controls.Add(this.lbGames);
            this.Controls.Add(this.lbTeams);
            this.Name = "Form1";
            this.Text = "Form1";
            this.Load += new System.EventHandler(this.Form1_Load);
            this.gbKreirajBilten.ResumeLayout(false);
            this.gbKreirajBilten.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.nud2)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.nudX)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.nud1)).EndInit();
            this.kbDodadiNatprevar.ResumeLayout(false);
            this.kbDodadiNatprevar.PerformLayout();
            this.gbUplati.ResumeLayout(false);
            this.gbUplati.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.nudUplata)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.ListBox lbTeams;
        private System.Windows.Forms.ListBox lbGames;
        private System.Windows.Forms.ListBox lbTicket;
        private System.Windows.Forms.GroupBox gbKreirajBilten;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.NumericUpDown nud2;
        private System.Windows.Forms.NumericUpDown nudX;
        private System.Windows.Forms.NumericUpDown nud1;
        private System.Windows.Forms.GroupBox kbDodadiNatprevar;
        private System.Windows.Forms.GroupBox gbUplati;
        private System.Windows.Forms.Button btnDodadiNovTim;
        private System.Windows.Forms.Button btnDodadiVoBilten;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.TextBox tbShifraNatprevar;
        private System.Windows.Forms.TextBox tbKoeficient;
        private System.Windows.Forms.ContextMenuStrip contextMenuStrip1;
        private System.Windows.Forms.Button btnPrint;
        private System.Windows.Forms.TextBox tbDobivka;
        private System.Windows.Forms.MaskedTextBox mtbCode;
        private System.Windows.Forms.ComboBox cbTim;
        private System.Windows.Forms.Button btnAddGane;
        private System.Windows.Forms.NumericUpDown nudUplata;
    }
}

