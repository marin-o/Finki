using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace AudsDrawing {
    public partial class Form1 : Form {
        public string ShapeType { get; set; } = "square";
        public Scene Scene { get; set; }
        public Form1() {
            InitializeComponent();
            Scene= new Scene(new List<Shape>());
            DoubleBuffered = true;
            timer1.Start();
        }

        private void Form1_MouseClick(object sender, MouseEventArgs e) {
            Scene.Click(e.Location, ShapeType);
            Invalidate();
        }

        private void Form1_Paint(object sender, PaintEventArgs e) {
            Scene.drawAll(e.Graphics);
        }

        private void timer1_Tick(object sender, EventArgs e) {
            Scene.Pulse();
            Invalidate();
        }

        private void circleToolStripMenuItem_Click(object sender, EventArgs e) {
            ShapeType = "circle";
        }

        private void squareToolStripMenuItem_Click(object sender, EventArgs e) {
            ShapeType = "square";
        }
    }
}
