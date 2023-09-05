using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Formatters.Binary;
using System.Security.Authentication.ExtendedProtection;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace AudsPies {
    public partial class Form1 : Form {
        public Scene Scene{ get; set; }
        public Form1() {
            InitializeComponent();
            Scene = new Scene();
            DoubleBuffered = true;
        }

        private void toolStripLabel1_Click(object sender, EventArgs e) {
            timer1.Start();
        }

        private void colorToolStripMenuItem_Click(object sender, EventArgs e) {
            ColorDialog dlg = new ColorDialog();
            if(dlg.ShowDialog() == DialogResult.OK) {
                Scene.PrimaryColor = dlg.Color;
            }
        }

        private void Form1_MouseDoubleClick(object sender, MouseEventArgs e) {
            Scene.AddCircle(e.Location);
            Invalidate();
        }

        private void timer1_Tick(object sender, EventArgs e) {
            Scene.Pulse();
            Invalidate();
        }

        private void Form1_Paint(object sender, PaintEventArgs e) {
            Scene.Draw(e.Graphics);
        }

        private void saveToolStripButton_Click(object sender, EventArgs e) {
            SaveFileDialog sfd = new SaveFileDialog();
            if( sfd.ShowDialog() == DialogResult.OK ) {
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
            }
        }
    }
}
