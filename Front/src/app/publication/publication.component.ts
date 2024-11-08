import { Component, OnInit } from '@angular/core';
import { Publication } from '../models/publication';  
import { PublicationService } from '../service/publication.service'

@Component({
  selector: 'app-publication',
  templateUrl: './publication.component.html',
  styleUrls: ['./publication.component.css']
})
export class PublicationComponent implements OnInit {
  publications: Publication[] = [];  // Liste des publications
  newPublication: Publication = new Publication();  // Nouvelle publication à ajouter
  selectedPublication: Publication | null = null;  // Publication sélectionnée pour la modification

  constructor(private publicationService: PublicationService) { }

  ngOnInit(): void {
    this.getPublications();  // Récupérer les publications au démarrage du composant
  }

  // Récupérer les publications
  getPublications(): void {
    this.publicationService.getPublications().subscribe(publications => {
      this.publications = publications;
    }, error => {
      console.error('Erreur lors de la récupération des publications:', error);
    });
  }

  // Ajouter une nouvelle publication
  addPublication(): void {
    this.publicationService.addPublication(this.newPublication).subscribe({
      next: (response) => {
        console.log('Publication ajoutée:', response);
        this.getPublications();  // Rafraîchir la liste des publications
        this.newPublication = new Publication();  // Réinitialiser le formulaire
      },
      error: (error) => {
        console.error('Erreur lors de l\'ajout de la publication:', error);
      }
    });
  }

  // Supprimer une publication
  deletePublication(publicationUri: string): void {
    this.publicationService.deletePublication(publicationUri).subscribe(response => {
      console.log('Publication supprimée:', response);
      this.getPublications();  // Rafraîchir la liste des publications
    }, error => {
      console.error('Erreur lors de la suppression de la publication:', error);
    });
  }

  // Sélectionner une publication pour la modification
  editPublication(publication: Publication): void {
    this.selectedPublication = { ...publication };  // Cloner la publication pour modification
  }

  // Mettre à jour une publication
  updatePublication(): void {
    if (this.selectedPublication) {
      this.publicationService.updatePublication(this.selectedPublication).subscribe(response => {
        console.log('Publication mise à jour:', response);
        this.getPublications();  // Rafraîchir la liste des publications
        this.selectedPublication = null;  // Réinitialiser la publication sélectionnée
      }, error => {
        console.error('Erreur lors de la mise à jour de la publication:', error);
      });
    }
  }

  // Annuler la modification et réinitialiser le formulaire
  cancelEdit(): void {
    this.selectedPublication = null;  // Réinitialiser la publication sélectionnée
  }
}


