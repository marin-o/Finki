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

namespace IspitniElipis {
    public partial class Form1 : Form {
        
        //public Point EndPoint { get; set; } = Point.Empty;
        public Scene Scene { get; set; }
        public Form1() {
            InitializeComponent();
            Scene = new Scene();
            DoubleBuffered = true;
            timerAddColor.Start();
        }

        private void Form1_Load(object sender, EventArgs e) {

        }

        private void Form1_MouseClick(object sender, MouseEventArgs e) {
            if( Scene.StartPoint.IsEmpty ) {
                Scene.StartPoint = e.Location;
                Scene.CurrentEllipse = new Ellipse(Scene.StartPoint, Scene.StartPoint);
            }
            else {
                Point endPoint = e.Location;
                Scene.CurrentEllipse.IsClosed = true;
                Scene.AddEllipse(Scene.CurrentEllipse);
                Scene.CurrentEllipse = null;
                Scene.StartPoint = Point.Empty;
                tsslCircles.Text = Scene.EllipseList.Count.ToString();
            }
            Invalidate();
        }

        private void Form1_MouseMove(object sender, MouseEventArgs e) {
            if( !Scene.StartPoint.IsEmpty ) {
                Scene.CurrentEllipse.EndPoint = e.Location;
            }
            Invalidate();
        }

        private void Form1_Paint(object sender, PaintEventArgs e) {
            Scene.Draw(e.Graphics);
        }

        private void timerAddColor_Tick(object sender, EventArgs e) {
            Scene.AddColor();
        }

        private void saveToolStripButton_Click(object sender, EventArgs e) {
            SaveFileDialog sfd = new SaveFileDialog();
            if( sfd.ShowDialog() == DialogResult.OK ) {
                IFormatter formatter = new BinaryFormatter();
                FileStream fs = new FileStream(sfd.FileName, FileMode.OpenOrCreate);
                formatter.Serialize(fs, Scene);
            }
        }

        private void openToolStripButton_Click(object sender, EventArgs e) {
            OpenFileDialog ofd = new OpenFileDialog();
            if( ofd.ShowDialog() == DialogResult.OK ) {
                IFormatter formatter = new BinaryFormatter();
                FileStream fs = new FileStream(ofd.FileName, FileMode.Open);
                this.Scene = formatter.Deserialize(fs) as Scene;
            }
        }
    }
}

