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

namespace IspitniPravoagolnci {
    public partial class Form1 : Form {
        public Scene Scene { get; set; }
        private Point start;
        private Point end;
        public Form1() {
            InitializeComponent();
            Scene = new Scene();
            start = Point.Empty; 
            end = Point.Empty;
            DoubleBuffered = true;
        }

        private void Form1_MouseDoubleClick(object sender, MouseEventArgs e) {
            if(start.IsEmpty) {
                start = e.Location;
            }
        }

        private bool InvalidPoints() {
            return start.X > end.X || start.Y > end.Y;
        }

        private void Form1_MouseMove(object sender, MouseEventArgs e) {
            end = e.Location;
            Invalidate();
        }

        private void Form1_Paint(object sender, PaintEventArgs e) {
            Scene.Draw(e.Graphics);
            if( !start.IsEmpty && !InvalidPoints()) {
                Pen p = new Pen(Color.Black);
                p.DashStyle = System.Drawing.Drawing2D.DashStyle.Dash;

                int width = Math.Abs(end.X - start.X);
                int height = Math.Abs(end.Y - start.Y);
                Size size = new Size(width, height);

                e.Graphics.DrawRectangle(p, new System.Drawing.Rectangle(start, size));
                p.Dispose();
            }
        }

        private void colorToolStripMenuItem_Click(object sender, EventArgs e) {
            ColorDialog cd = new ColorDialog();
            if(cd.ShowDialog() == DialogResult.OK ) {
                Scene.Color = cd.Color;
            }
        }

        private void Form1_MouseClick(object sender, MouseEventArgs e) {
            if( InvalidPoints() ) {
                MessageBox.Show("Invalid Points!", "Try again!", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                start = Point.Empty;
                end = Point.Empty;
                return;
            }
            if(e.Button == MouseButtons.Left && !start.IsEmpty) {
                Scene.AddRectangle(start,e.Location);
                start = Point.Empty;
                end = Point.Empty;
                updateTssl();
            }
            else {
                Scene.Click(e.Location);
            }
        }

        private void saveToolStripButton_Click(object sender, EventArgs e) {
            SaveFileDialog sfd = new SaveFileDialog();
            if(sfd.ShowDialog() == DialogResult.OK ) {
                IFormatter f = new BinaryFormatter();
                FileStream fs = new FileStream(sfd.FileName, FileMode.OpenOrCreate);
                f.Serialize(fs, Scene);
            }
        }

        private void openToolStripButton_Click(object sender, EventArgs e) {
            OpenFileDialog sfd = new OpenFileDialog();
            if( sfd.ShowDialog() == DialogResult.OK ) {
                IFormatter f = new BinaryFormatter();
                FileStream fs = new FileStream(sfd.FileName, FileMode.Open);
                Scene = f.Deserialize(fs) as Scene;

                Invalidate();
                updateTssl();
            }
        }

        private void Form1_KeyPress(object sender, KeyPressEventArgs e) {
        }
        
        private void updateTssl() {
            tsslRects.Text = Scene.Rectangles.Count.ToString();
        }

        private void Form1_KeyDown(object sender, KeyEventArgs e) {
            if(e.KeyCode == Keys.Delete) {
                Scene.Rectangles.Clear();
                updateTssl();
                Invalidate();
            }
        }
    }
}
