using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Formatters.Binary;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace AudsFlyingBalls {
    public partial class Form1 : Form {
        public Scene Scene { get; set; }
        public Form1() {
            InitializeComponent();
            Scene = new Scene(this.Height, this.Width);
            timerSecond.Start();
            timerCentisecond.Start();
            this.DoubleBuffered = true;
            pauseToolStripMenuItem.BackColor = Color.Green;
            pauseToolStripMenuItem.ForeColor = Color.Black;

        }

        private void Form1_Paint(object sender, PaintEventArgs e) {
            Scene.Draw(e.Graphics);
        }


        private void timerSecond_Tick(object sender, EventArgs e) {
            Scene.AddBall();
            Invalidate();
        }
        private void UpdateStatusLabels() {
            tsslHits.Text = $"Hits: {Scene.NotMissed}";
            tsslMisses.Text = $"Misses: {Scene.Missed}";
        }
        private void timerCentisecond_Tick(object sender, EventArgs e) {
            Scene.Move();
            UpdateStatusLabels();
            Invalidate();
        }


        private void Form1_SizeChanged(object sender, EventArgs e) {
            Scene.Height = this.Height;
            Scene.Width = this.Width;
        }

        private void Form1_MouseClick(object sender, MouseEventArgs e) {
            Scene.Hit(e.Location);
        }

        private void saveToolStripMenuItem_Click(object sender, EventArgs e) {
            pauseToolStripMenuItem_Click(null, null);
            SaveFileDialog saveFileDialog = new SaveFileDialog();
            if(saveFileDialog.ShowDialog() == DialogResult.OK ) {
                FileStream fs = new FileStream(saveFileDialog.FileName, FileMode.OpenOrCreate);
                IFormatter formatter = new BinaryFormatter();
                formatter.Serialize(fs, Scene);
            }
        }

        private void openToolStripMenuItem_Click(object sender, EventArgs e) {
            pauseToolStripMenuItem_Click(null, null);
            OpenFileDialog openFileDialog = new OpenFileDialog();
            if(openFileDialog.ShowDialog() == DialogResult.OK ) {
                FileStream fs = new FileStream(openFileDialog.FileName, FileMode.Open);
                IFormatter formatter = new BinaryFormatter();
                Scene = formatter.Deserialize(fs) as Scene;
            }
            Invalidate();
        }

        private void newToolStripMenuItem_Click(object sender, EventArgs e) {
            Scene = new Scene(this.Height, this.Width);
            Invalidate();
        }

        private void pauseToolStripMenuItem_Click(object sender, EventArgs e) {
            Scene.Paused = !Scene.Paused;
            if( Scene.Paused ) {
                pauseToolStripMenuItem.Text = "Unpause";
                pauseToolStripMenuItem.BackColor = Color.Red;
            }
            else {
                pauseToolStripMenuItem.Text = "Pause";
                pauseToolStripMenuItem.ForeColor = Color.Green;
            }
            pauseToolStripMenuItem.ForeColor = Color.Black;
        }
    }
}
