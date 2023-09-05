namespace AudListBox {
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
            this.clbStrings = new System.Windows.Forms.CheckedListBox();
            this.lbItems = new System.Windows.Forms.ListBox();
            this.tbNewItem = new System.Windows.Forms.TextBox();
            this.btnAddItem = new System.Windows.Forms.Button();
            this.btnMoveItem = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // clbStrings
            // 
            this.clbStrings.FormattingEnabled = true;
            this.clbStrings.Location = new System.Drawing.Point(523, 12);
            this.clbStrings.Name = "clbStrings";
            this.clbStrings.Size = new System.Drawing.Size(265, 289);
            this.clbStrings.TabIndex = 0;
            // 
            // lbItems
            // 
            this.lbItems.FormattingEnabled = true;
            this.lbItems.Location = new System.Drawing.Point(12, 12);
            this.lbItems.Name = "lbItems";
            this.lbItems.Size = new System.Drawing.Size(207, 303);
            this.lbItems.TabIndex = 1;
            // 
            // tbNewItem
            // 
            this.tbNewItem.Location = new System.Drawing.Point(12, 330);
            this.tbNewItem.Name = "tbNewItem";
            this.tbNewItem.Size = new System.Drawing.Size(100, 20);
            this.tbNewItem.TabIndex = 2;
            // 
            // btnAddItem
            // 
            this.btnAddItem.Location = new System.Drawing.Point(22, 356);
            this.btnAddItem.Name = "btnAddItem";
            this.btnAddItem.Size = new System.Drawing.Size(75, 23);
            this.btnAddItem.TabIndex = 3;
            this.btnAddItem.Text = "Dodadi";
            this.btnAddItem.UseVisualStyleBackColor = true;
            this.btnAddItem.Click += new System.EventHandler(this.btnAddItem_Click);
            // 
            // btnMoveItem
            // 
            this.btnMoveItem.Location = new System.Drawing.Point(247, 140);
            this.btnMoveItem.Name = "btnMoveItem";
            this.btnMoveItem.Size = new System.Drawing.Size(249, 23);
            this.btnMoveItem.TabIndex = 4;
            this.btnMoveItem.Text = "Premesti";
            this.btnMoveItem.UseVisualStyleBackColor = true;
            this.btnMoveItem.Click += new System.EventHandler(this.btnMoveItem_Click);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 450);
            this.Controls.Add(this.btnMoveItem);
            this.Controls.Add(this.btnAddItem);
            this.Controls.Add(this.tbNewItem);
            this.Controls.Add(this.lbItems);
            this.Controls.Add(this.clbStrings);
            this.Name = "Form1";
            this.Text = "Form1";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.CheckedListBox clbStrings;
        private System.Windows.Forms.ListBox lbItems;
        private System.Windows.Forms.TextBox tbNewItem;
        private System.Windows.Forms.Button btnAddItem;
        private System.Windows.Forms.Button btnMoveItem;
    }
}

