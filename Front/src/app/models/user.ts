export class User {
    username!: string;      // Nom d'utilisateur (doit être unique)
    password!: string;      // Mot de passe (veillez à le gérer de manière sécurisée, pas en texte brut)
    email!: string;         // Adresse e-mail de l'utilisateur
    name!: string;          // Nom complet de l'utilisateur
    role!: number;          // Rôle de l'utilisateur (par exemple 'admin', 'user', 'moderator', etc.)
    
  }
  