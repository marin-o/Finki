namespace IspitniBuses {
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
            this.lbBuses = new System.Windows.Forms.ListBox();
            this.lbLines = new System.Windows.Forms.ListBox();
            this.btnAddBus = new System.Windows.Forms.Button();
            this.btnDelBus = new System.Windows.Forms.Button();
            this.btnAddLine = new System.Windows.Forms.Button();
            this.tbMostExpensive = new System.Windows.Forms.TextBox();
            this.tbAvgPrice = new System.Windows.Forms.TextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.groupBox1 = new System.Windows.Forms.GroupBox();
            this.SuspendLayout();
            // 
            // lbBuses
            // 
            this.lbBuses.FormattingEnabled = true;
            this.lbBuses.Location = new System.Drawing.Point(12, 12);
            this.lbBuses.Name = "lbBuses";
            this.lbBuses.Size = new System.Drawing.Size(316, 251);
            this.lbBuses.TabIndex = 0;
            this.lbBuses.SelectedIndexChanged += new System.EventHandler(this.lbBuses_SelectedIndexChanged);
            // 
            // lbLines
            // 
            this.lbLines.FormattingEnabled = true;
            this.lbLines.Location = new System.Drawing.Point(334, 12);
            this.lbLines.Name = "lbLines";
            this.lbLines.Size = new System.Drawing.Size(454, 251);
            this.lbLines.TabIndex = 1;
            // 
            // btnAddBus
            // 
            this.btnAddBus.Location = new System.Drawing.Point(13, 270);
            this.btnAddBus.Name = "btnAddBus";
            this.btnAddBus.Size = new System.Drawing.Size(315, 23);
            this.btnAddBus.TabIndex = 2;
            this.btnAddBus.Text = "Dodadi bus";
            this.btnAddBus.UseVisualStyleBackColor = true;
            this.btnAddBus.Click += new System.EventHandler(this.btnAddBus_Click);
            // 
            // btnDelBus
            // 
            this.btnDelBus.Location = new System.Drawing.Point(12, 310);
            this.btnDelBus.Name = "btnDelBus";
            this.btnDelBus.Size = new System.Drawing.Size(315, 23);
            this.btnDelBus.TabIndex = 3;
            this.btnDelBus.Text = "izbrisi bus";
            this.btnDelBus.UseVisualStyleBackColor = true;
            this.btnDelBus.Click += new System.EventHandler(this.btnDelBus_Click);
            // 
            // btnAddLine
            // 
            this.btnAddLine.Location = new System.Drawing.Point(12, 415);
            this.btnAddLine.Name = "btnAddLine";
            this.btnAddLine.Size = new System.Drawing.Size(315, 23);
            this.btnAddLine.TabIndex = 4;
            this.btnAddLine.Text = "dodadi linija";
            this.btnAddLine.UseVisualStyleBackColor = true;
            this.btnAddLine.Click += new System.EventHandler(this.btnAddLine_Click);
            // 
            // tbMostExpensive
            // 
            this.tbMostExpensive.Enabled = false;
            this.tbMostExpensive.Location = new System.Drawing.Point(385, 310);
            this.tbMostExpensive.Name = "tbMostExpensive";
            this.tbMostExpensive.Size = new System.Drawing.Size(403, 20);
            this.tbMostExpensive.TabIndex = 5;
            // 
            // tbAvgPrice
            // 
            this.tbAvgPrice.Enabled = false;
            this.tbAvgPrice.Location = new System.Drawing.Point(385, 415);
            this.tbAvgPrice.Name = "tbAvgPrice";
            this.tbAvgPrice.Size = new System.Drawing.Size(403, 20);
            this.tbAvgPrice.TabIndex = 6;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(385, 291);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(81, 13);
            this.label1.TabIndex = 7;
            this.label1.Text = "Najskapa linihja";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(385, 399);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(120, 13);
            this.label2.TabIndex = 8;
            this.label2.Text = "Prosecna cena na liniite";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(10, -4);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(48, 13);
            this.label3.TabIndex = 9;
            this.label3.Text = "Avtobusi";
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(334, -4);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(25, 13);
            this.label4.TabIndex = 10;
            this.label4.Text = "Linii";
            // 
            // groupBox1
            // 
            this.groupBox1.Location = new System.Drawing.Point(337, 270);
            this.groupBox1.Name = "groupBox1";
            this.groupBox1.Size = new System.Drawing.Size(451, 165);
            this.groupBox1.TabIndex = 11;
            this.groupBox1.TabStop = false;
            this.groupBox1.Text = "Linii";
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 450);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.tbAvgPrice);
            this.Controls.Add(this.tbMostExpensive);
            this.Controls.Add(this.btnAddLine);
            this.Controls.Add(this.btnDelBus);
            this.Controls.Add(this.btnAddBus);
            this.Controls.Add(this.lbLines);
            this.Controls.Add(this.lbBuses);
            this.Controls.Add(this.groupBox1);
            this.Name = "Form1";
            this.Text = "Form1";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.ListBox lbBuses;
        private System.Windows.Forms.ListBox lbLines;
        private System.Windows.Forms.Button btnAddBus;
        private System.Windows.Forms.Button btnDelBus;
        private System.Windows.Forms.Button btnAddLine;
        private System.Windows.Forms.TextBox tbMostExpensive;
        private System.Windows.Forms.TextBox tbAvgPrice;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.GroupBox groupBox1;
    }
}

