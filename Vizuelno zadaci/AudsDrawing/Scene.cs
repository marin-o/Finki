using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Runtime.CompilerServices;
using System.Text;
using System.Threading.Tasks;

namespace AudsDrawing {
    public class Scene {
        public List<Shape> Shapes { get; set; }
        public Shape SelectedShape { get; set; } = null;
        public Scene(List<Shape> shapes) {
            Shapes = shapes;
        }

        public void addShape(Shape shape) {
            Shapes.Add(shape);
        }

        public void drawAll(Graphics g) {
            foreach (Shape shape in Shapes) {
                shape.Draw(g);
            }
        }

        public void Click(Point p, string shapeType) {
            bool found = false;

            foreach (Shape shape in Shapes) {
                if( shape.IsHit(p) ) {
                    found = true;
                    if( shape.Equals(SelectedShape) ) {
                        shape.Selected = false;
                        SelectedShape = null;
                        break;
                    } else if (SelectedShape == null ) {
                        SelectedShape = shape;
                        shape.Selected = true;
                        break;
                    }
                }
            }
            if (!found) {
                if( shapeType == "circle" ) {
                    Shapes.Add(new Circle(Color.Red, 20, p));
                }
                else {
                    Shapes.Add(new Square(Color.Blue, 20, p));
                }
            }
        }

        public void Pulse() {
            if(SelectedShape != null)
                SelectedShape.Pulse();
        }
    }
}
