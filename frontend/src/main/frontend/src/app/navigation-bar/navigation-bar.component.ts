import { Component } from '@angular/core';
import { CloudInstancesTableComponent } from '../cloud-instances-table/cloud-instances-table.component';
import { Stormpath, Account } from 'angular-stormpath';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-navigation-bar',
  templateUrl: './navigation-bar.component.html',
  styleUrls: ['./navigation-bar.component.css']
})

export class NavigationBarComponent {
  private user$: Observable<Account | boolean>;
  private loggedIn$: Observable<boolean>;
  private login: boolean;
  private register: boolean;

  constructor(public stormpath: Stormpath) {
  }

  ngOnInit() {
    this.login = true;
    this.register = false;
    this.user$ = this.stormpath.user$;
    this.loggedIn$ = this.user$.map(user => !!user);
  }

  showLogin() {
    this.login = !(this.register = false);
  }

  showRegister() {
    this.register = !(this.login = false);
  }

  logout() {
    this.stormpath.logout();
  }
}
