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
using System.Text.Json;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace AudsPolygons {
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

        private void saveToolStripMenuItem_Click(object sender, EventArgs e) {
            SaveFileDialog saveFileDialog = new SaveFileDialog();
            saveFileDialog.Title = "Saving time";
            if (saveFileDialog.ShowDialog() == DialogResult.OK ) {
                //FileStream fs = new FileStream(saveFileDialog.FileName, FileMode.OpenOrCreate);
                //IFormatter formatter = new BinaryFormatter();
                //formatter.Serialize(fs, Scene);
                string filename = saveFileDialog.FileName;
                string jsonString = JsonSerializer.Serialize(Scene);
                File.WriteAllText(filename, jsonString); //system cannot read the file because it is being used by another process how to fix this? 
            }
        }

        private void openToolStripMenuItem_Click(object sender, EventArgs e) {
            OpenFileDialog openFileDialog = new OpenFileDialog();
            openFileDialog.Title = "Opening time";
            if(openFileDialog.ShowDialog() == DialogResult.OK ) {
                //FileStream fs = new FileStream(openFileDialog.FileName, FileMode.Open);
                IFormatter formatter = new BinaryFormatter();
                
                //Scene = formatter.Deserialize(fs) as Scene;
                Scene = JsonSerializer.Deserialize<Scene>(File.ReadAllText(openFileDialog.FileName)); // System.Text.Json.JsonException: ''C' is an invalid start of a value. Path: $ | LineNumber: 0 | BytePositionInLine: 0.'

            }
        }

        private void newToolStripMenuItem_Click(object sender, EventArgs e) {
            Scene = new Scene();
            Invalidate();
        }
    }
}
