using GemBox.Document;
using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json;
using Org.BouncyCastle.Asn1.X509;
using System.Text;
using VezbanjeAdminApp.Models;

namespace VezbanjeAdminApp.Controllers
{
    public class VaccinationCentersController : Controller
    {
        public VaccinationCentersController()
        {
            ComponentInfo.SetLicense("FREE-LIMITED-KEY");
        }
        public IActionResult Index()
        {
            HttpClient client = new HttpClient();
            string URL = "https://localhost:44331/api/Admin/GetAllCenters";

            HttpResponseMessage response = client.GetAsync(URL).Result;
            var data = response.Content.ReadAsAsync<List<VaccinationCenter>>().Result;
            return View(data);
        }
        public IActionResult Details(string id)
        {
            HttpClient client = new HttpClient();
            //added in next aud
            string URL = "https://localhost:44331/api/Admin/GetDetails";
            var model = new
            {
                Id = id
            };

            HttpContent content = new StringContent(JsonConvert.SerializeObject(model), Encoding.UTF8, "application/json");

            HttpResponseMessage response = client.PostAsync(URL, content).Result;

            var result = response.Content.ReadAsAsync<VaccinationCenter>().Result;


            return View(result);

        }

        public FileContentResult CreateInvoice(string id)
        {
            HttpClient client = new HttpClient();

            string URL = "https://localhost:44331/api/Admin/GetDetails";
            var model = new
            {
                Id = id
            };

            HttpContent content = new StringContent(JsonConvert.SerializeObject(model), Encoding.UTF8, "application/json");

            HttpResponseMessage response = client.PostAsync(URL, content).Result;

            var result = response.Content.ReadAsAsync<VaccinationCenter>().Result;

            var templatePath = Path.Combine(Directory.GetCurrentDirectory(), "Invoice.docx");
            var document = DocumentModel.Load(templatePath);

            document.Content.Replace("{{OrderNumber}}", result.Id.ToString());
            

            StringBuilder sb = new StringBuilder();
            //var total = 0;
            foreach (var item in result.Vaccines)
            {
                sb.AppendLine("Vaccine: " + item.Certificate + " has Patient " + item?.PatientFor?.FirstName + " from center" + item?.Center?.Name);
                //total += (item.Quantity * item.Product.Price);
            }
            document.Content.Replace("{{ProductList}}", sb.ToString());
            document.Content.Replace("{{TotalPrice}}", result.Vaccines.Count() + "$");

            var stream = new MemoryStream();
            document.Save(stream, new PdfSaveOptions());
            return File(stream.ToArray(), new PdfSaveOptions().ContentType, "ExportInvoice.pdf");

        }

    }
}
