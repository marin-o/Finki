using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IspitniPravoagolnci {
    [Serializable]
    public class Scene {
        public List<Rectangle> Rectangles { get; set; } = new List<Rectangle>();
        public Color Color { get; set; } = Color.Blue;
        public void Draw(Graphics g) {
            foreach (var rect in Rectangles) {
                rect.Draw(g);
            }
        }

        public void Click(Point location) {
            foreach(var rect in Rectangles) {
                if( rect.Click(location) )
                    break;
            }
        }

        public void AddRectangle(Point p1, Point p2 ) {
            Rectangles.Add(new Rectangle(p1, p2, Color));
        }
    }
}
