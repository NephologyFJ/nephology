import { Component } from '@angular/core';
import { Stormpath } from 'angular-stormpath';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  private user$: Observable<Account | boolean>;

  constructor(public stormpath: Stormpath) {
  }

  ngOnInit() {
    this.user$ = this.stormpath.user$;
  }

}
