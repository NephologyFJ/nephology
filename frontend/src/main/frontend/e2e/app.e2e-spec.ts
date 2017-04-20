import { NephologyPage } from './app.po';

describe('nephology App', () => {
  let page: NephologyPage;

  beforeEach(() => {
    page = new NephologyPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
