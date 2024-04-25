using System;
using System.CodeDom.Compiler;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IspitniPravoagolnci {
    [Serializable]
    public class Rectangle {
        public Point StartPoint { get; set; }
        public Point EndPoint { get; set; }
        public Color Color { get; set; } = Color.Blue;
        public bool IsSelected { get; set; } = false;
        private int width, height;

        public Rectangle(Point sp, Point ep, Color color) {
            StartPoint = sp;
            EndPoint = ep;
            Color = color;
            if(StartPoint.X > EndPoint.X || StartPoint.Y > EndPoint.Y) {
                Point temp = StartPoint;
                StartPoint = EndPoint;
                EndPoint = StartPoint;
            }
            width = Math.Abs(StartPoint.X - EndPoint.X);
            height = Math.Abs(StartPoint.Y - EndPoint.Y);
        }

        public void Draw(Graphics g) {
            if( IsSelected ) {
                Pen p = new Pen(Color.Red);
                g.DrawRectangle(p, StartPoint.X-1, StartPoint.Y-1, width+1, height+1);
                p.Dispose();
            }
            Brush b = new SolidBrush(Color);
            g.FillRectangle(b, StartPoint.X, StartPoint.Y, width, height);
            b.Dispose();
        }
        
        public bool Click(Point location) {
            System.Drawing.Rectangle r = new System.Drawing.Rectangle(StartPoint.X, StartPoint.Y,width,height);
            if( r.Contains(location) ) {
                IsSelected = !IsSelected;
                return true;
            }
            return false;
        }
    }
}
