using AdminApplication.Models;
using ExcelDataReader;
using GemBox.Document;
using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json;
using Org.BouncyCastle.Asn1.X509;
using System.Text;

namespace AdminApplication.Controllers
{
    public class HealthExaminationsController : Controller
    {
        public HealthExaminationsController() {
            ComponentInfo.SetLicense("FREE-LIMITED-KEY");
        }
        public IActionResult Index()
        {
            HttpClient client = new HttpClient();
            string URL = "https://localhost:44305/api/Admin/GetAllHealthExaminations";

            HttpResponseMessage response = client.GetAsync(URL).Result;
            var data = response.Content.ReadAsAsync<List<HealthExamination>>().Result;
            return View(data);
        }

        public IActionResult Details(string id)
        {
            HttpClient client = new HttpClient();
            //added in next aud
            string URL = "https://localhost:44305/api/Admin/GetDetailsForHealthExamination";
            var model = new
            {
                Id = id
            };

            HttpContent content = new StringContent(JsonConvert.SerializeObject(model), Encoding.UTF8, "application/json");

            HttpResponseMessage response = client.PostAsync(URL, content).Result;

            var result = response.Content.ReadAsAsync<HealthExamination>().Result;


            return View(result);

        }

        public IActionResult ImportExaminations()
        {
            return View();
        }

        [HttpPost]
        public IActionResult ImportExaminationsMain(IFormFile file)
        {
            string pathToUpload = $"{Directory.GetCurrentDirectory()}\\files\\{file.FileName}";

            using (FileStream fileStream = System.IO.File.Create(pathToUpload))
            {
                file.CopyTo(fileStream);
                fileStream.Flush();
            }

            List<HealthExamination> users = getAllExaminationsfromFile(file.FileName);
            HttpClient client = new HttpClient();
            string URL = "https://localhost:44305/api/Admin/ImportExaminations";

            HttpContent content = new StringContent(JsonConvert.SerializeObject(users), Encoding.UTF8, "application/json");

            HttpResponseMessage response = client.PostAsync(URL, content).Result;

            var result = response.Content.ReadAsAsync<bool>().Result;

            return RedirectToAction("Index");

        }

        private List<HealthExamination> getAllExaminationsfromFile(string fileName)
        {
            List<HealthExamination> users = new List<HealthExamination>();
            string filePath = $"{Directory.GetCurrentDirectory()}\\files\\{fileName}";

            System.Text.Encoding.RegisterProvider(System.Text.CodePagesEncodingProvider.Instance);

            using (var stream = System.IO.File.Open(filePath, FileMode.Open, FileAccess.Read))
            {
                using (var reader = ExcelReaderFactory.CreateReader(stream))
                {
                    while (reader.Read())
                    {
                        users.Add(new Models.HealthExamination
                        {
                            Description = reader.GetValue(0).ToString(),
                            EmployeeId = Guid.Parse(reader.GetValue(1).ToString()),
                            PolyclinicId = Guid.Parse(reader.GetValue(2).ToString())
                        });
                    }

                }
            }
            return users;

        }
    }
}
