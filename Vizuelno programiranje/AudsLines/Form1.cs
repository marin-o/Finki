using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace AudsLines {
    public partial class Form1 : Form {
        public Scene Scene { get; set; }
        public Form1() {
            InitializeComponent();
            Scene = new Scene(this.Width,this.Height);
            this.DoubleBuffered = true;
        }

        private void UpdateStatus() {
            linesStatusLabel.Text = $"Lines: {Scene.Lines.Count.ToString()}";
        }
        private void Form1_MouseClick(object sender, MouseEventArgs e) {
            Scene.AddPoint(e.Location);
            Scene.UndoStack.Clear();
            Invalidate();
        }

        private void Form1_Paint(object sender, PaintEventArgs e) {
            Scene.Draw(e.Graphics);
        }

        private void colorToolStripMenuItem_Click(object sender, EventArgs e) {
            ColorDialog dlg = new ColorDialog();
            
            if ( dlg.ShowDialog() == DialogResult.OK ) {
                Scene.Color = dlg.Color;
            }
        }

        private void thinToolStripMenuItem_Click(object sender, EventArgs e) {
            thinToolStripMenuItem.Checked = true;
            normalToolStripMenuItem.Checked = false;
            thickToolStripMenuItem.Checked = false;
            Scene.Thickness = 1;
        }

        private void normalToolStripMenuItem_Click(object sender, EventArgs e) {
            thinToolStripMenuItem.Checked = false;
            normalToolStripMenuItem.Checked = true;
            thickToolStripMenuItem.Checked = false;
            Scene.Thickness = 2;
        }

        private void thickToolStripMenuItem_Click(object sender, EventArgs e) {
            thinToolStripMenuItem.Checked = false;
            normalToolStripMenuItem.Checked = false;
            thickToolStripMenuItem.Checked = true;
            Scene.Thickness = 3;
        }

        private void Form1_MouseMove(object sender, MouseEventArgs e) {
            Scene.Cursor = e.Location;
            Invalidate();
        }

        private void positionerToolStripMenuItem_Click(object sender, EventArgs e) {
            Scene.Positioner = !Scene.Positioner;
            positionerToolStripMenuItem.Checked = Scene.Positioner;
        }

        private void undoToolStripMenuItem_Click(object sender, EventArgs e) {
            Scene.Undo();
            UpdateStatus();
            Invalidate();
        }

        private void redoToolStripMenuItem_Click(object sender, EventArgs e) {
            Scene.Redo();
            UpdateStatus();
            Invalidate();
        }
    }
}
