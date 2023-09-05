using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace IspitniPolygons {
    public partial class Form1 : Form {
        public Scene Scene { get; set; }
        public Form1() {
            InitializeComponent();
            Scene = new Scene();
            DoubleBuffered = true;
        }

        private void Form1_MouseClick(object sender, MouseEventArgs e) {
            Scene.AddPoint(e.Location);
            Invalidate();
        }

        private void Form1_MouseMove(object sender, MouseEventArgs e) {
            Scene.UpdateCursor(e.Location);
            Invalidate();
        }

        private void Form1_Paint(object sender, PaintEventArgs e) {
            Scene.Draw(e.Graphics);
        }

        private void colorToolStripMenuItem_Click(object sender, EventArgs e) {
            ColorDialog colorDialog = new ColorDialog();
            if(colorDialog.ShowDialog() == DialogResult.OK) {
                Scene.UpdateColor(colorDialog.Color);
            }
        }

        private void Form1_KeyDown(object sender, KeyEventArgs e) {
            Scene.Move(e.KeyCode);
            Invalidate();
        }
    }
}
