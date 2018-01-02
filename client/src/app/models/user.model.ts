export class User {
  constructor() {
  }

  id: string;
  authorities: Array<string>;
  username: string;
  password: string;
  linkedInLoginEnabled: boolean;
  twitterLoginEnabled: boolean;
  facebookLoginEnabled: boolean;
  accountNonExpired: boolean;
  accountNonLocked: boolean;
  credentialsNonExpired: boolean;
  enabled: boolean;
  createdAt: string;
  updatedAt: string;
}