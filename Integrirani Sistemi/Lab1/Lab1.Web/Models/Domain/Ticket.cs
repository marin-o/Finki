using Lab1.Web.Models.Identity;
using Microsoft.AspNetCore.Mvc;
using System.ComponentModel.DataAnnotations;

namespace Lab1.Web.Models.Domain
{
    public class Ticket
    {
        public Guid Id { get; set; }
        public string? TheaterId { get; set; }
        public TheaterShow? TheaterShow { get; set; }
        public string? UserId { get; set; }
        public TheaterUser? TheaterUser { get; set; }
        [Required]
        public int? Price { get; set; }

    }
}
