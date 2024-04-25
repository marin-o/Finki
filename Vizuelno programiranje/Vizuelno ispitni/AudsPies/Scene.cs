using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace AudsPies {
    [Serializable] public class Scene {
        public List<Circle> Circles { get; set; } = new List<Circle>();
        public Color PrimaryColor { get; set; } = Color.Red;
        public void Draw(Graphics g) {
            foreach (Circle c in Circles) {
                c.Draw(g);
            }
        }
        public void AddCircle(Point location) {
            Circle circle = new Circle(PrimaryColor, location);
            Circles.Add(circle);
        }

        public void Pulse() {
            foreach (Circle c in Circles) {
                c.Pulse();
            }
        }
    }
}
