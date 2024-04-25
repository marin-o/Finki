using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IspitniElipis {
    [Serializable]
    public class Scene {
        public List<Ellipse> EllipseList { get; set; } = new List<Ellipse>();
        public Ellipse CurrentEllipse { get; set; }
        public Point StartPoint { get; set; } = Point.Empty;
        public void AddEllipse(Ellipse ellipse) {
            EllipseList.Add(ellipse);
        }

        public void Draw(Graphics g) {
            if(CurrentEllipse != null)
                DrawCurrentEllipse(g);

            foreach (Ellipse ellipse in EllipseList)
                ellipse.Draw(g);
        }
        public void AddColor() {
            foreach (Ellipse ellipse in EllipseList)
                ellipse.AddColor();
        }

        public void DrawCurrentEllipse(Graphics g) {
            if (CurrentEllipse != null)
                CurrentEllipse.Draw(g);
        }
    }
}
