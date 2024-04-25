using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace AudsPolygons {
    [Serializable]
    public class Scene {
        public List<Polygon> Polygons { get; set; } = new List<Polygon>();
        public Polygon CurrentPolygon { get; set; } = new Polygon();

        public void Draw(Graphics g) {
            foreach (Polygon p in Polygons)
            {
                p.Draw(g);
            }
            CurrentPolygon.Draw(g);
        }

        public void AddPoint(Point point) {
            CurrentPolygon.AddPoint(point);
            if(CurrentPolygon.IsClosed) {
                Polygons.Add(CurrentPolygon);
                CurrentPolygon = new Polygon();
            }
        }

        public void UpdateCursor(Point location) {
            CurrentPolygon.MoveCursor(location);
        }
    }
}
