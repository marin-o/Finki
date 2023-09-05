namespace AudsHangman {
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
            this.lbWordState = new System.Windows.Forms.Label();
            this.timer1 = new System.Windows.Forms.Timer(this.components);
            this.lbTimer = new System.Windows.Forms.Label();
            this.tbAlphabet = new System.Windows.Forms.TextBox();
            this.tbGuessLetter = new System.Windows.Forms.TextBox();
            this.btnGuessLetter = new System.Windows.Forms.Button();
            this.pbAttempts = new System.Windows.Forms.ProgressBar();
            this.pbTime = new System.Windows.Forms.ProgressBar();
            this.SuspendLayout();
            // 
            // lbWordState
            // 
            this.lbWordState.AutoSize = true;
            this.lbWordState.Font = new System.Drawing.Font("Microsoft Sans Serif", 30F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lbWordState.Location = new System.Drawing.Point(12, 119);
            this.lbWordState.Name = "lbWordState";
            this.lbWordState.Size = new System.Drawing.Size(126, 46);
            this.lbWordState.TabIndex = 0;
            this.lbWordState.Text = "label1";
            // 
            // timer1
            // 
            this.timer1.Interval = 1000;
            this.timer1.Tick += new System.EventHandler(this.timer1_Tick);
            // 
            // lbTimer
            // 
            this.lbTimer.AutoSize = true;
            this.lbTimer.Location = new System.Drawing.Point(753, 13);
            this.lbTimer.Name = "lbTimer";
            this.lbTimer.Size = new System.Drawing.Size(35, 13);
            this.lbTimer.TabIndex = 1;
            this.lbTimer.Text = "label2";
            // 
            // tbAlphabet
            // 
            this.tbAlphabet.Enabled = false;
            this.tbAlphabet.Font = new System.Drawing.Font("Microsoft Sans Serif", 23F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.tbAlphabet.Location = new System.Drawing.Point(20, 258);
            this.tbAlphabet.Name = "tbAlphabet";
            this.tbAlphabet.Size = new System.Drawing.Size(768, 42);
            this.tbAlphabet.TabIndex = 2;
            // 
            // tbGuessLetter
            // 
            this.tbGuessLetter.Font = new System.Drawing.Font("Microsoft Sans Serif", 30F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.tbGuessLetter.Location = new System.Drawing.Point(20, 186);
            this.tbGuessLetter.MaxLength = 1;
            this.tbGuessLetter.Name = "tbGuessLetter";
            this.tbGuessLetter.Size = new System.Drawing.Size(67, 53);
            this.tbGuessLetter.TabIndex = 3;
            // 
            // btnGuessLetter
            // 
            this.btnGuessLetter.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnGuessLetter.Location = new System.Drawing.Point(93, 186);
            this.btnGuessLetter.Name = "btnGuessLetter";
            this.btnGuessLetter.Size = new System.Drawing.Size(75, 53);
            this.btnGuessLetter.TabIndex = 4;
            this.btnGuessLetter.Text = "Guess";
            this.btnGuessLetter.UseVisualStyleBackColor = true;
            this.btnGuessLetter.Click += new System.EventHandler(this.btnGuessLetter_Click);
            // 
            // pbAttempts
            // 
            this.pbAttempts.Location = new System.Drawing.Point(20, 333);
            this.pbAttempts.Maximum = 5;
            this.pbAttempts.Name = "pbAttempts";
            this.pbAttempts.Size = new System.Drawing.Size(768, 23);
            this.pbAttempts.Step = 1;
            this.pbAttempts.TabIndex = 5;
            // 
            // pbTime
            // 
            this.pbTime.Location = new System.Drawing.Point(20, 377);
            this.pbTime.Maximum = 120;
            this.pbTime.Name = "pbTime";
            this.pbTime.Size = new System.Drawing.Size(768, 23);
            this.pbTime.Step = -1;
            this.pbTime.TabIndex = 6;
            this.pbTime.Value = 120;
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 450);
            this.Controls.Add(this.pbTime);
            this.Controls.Add(this.pbAttempts);
            this.Controls.Add(this.btnGuessLetter);
            this.Controls.Add(this.tbGuessLetter);
            this.Controls.Add(this.tbAlphabet);
            this.Controls.Add(this.lbTimer);
            this.Controls.Add(this.lbWordState);
            this.Name = "Form1";
            this.Text = "Form1";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label lbWordState;
        private System.Windows.Forms.Timer timer1;
        private System.Windows.Forms.Label lbTimer;
        private System.Windows.Forms.TextBox tbAlphabet;
        private System.Windows.Forms.TextBox tbGuessLetter;
        private System.Windows.Forms.Button btnGuessLetter;
        private System.Windows.Forms.ProgressBar pbAttempts;
        private System.Windows.Forms.ProgressBar pbTime;
    }
}

