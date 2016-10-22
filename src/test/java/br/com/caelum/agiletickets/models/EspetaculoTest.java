package br.com.caelum.agiletickets.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Assert;
import org.junit.Test;


public class EspetaculoTest {

	private Espetaculo espetaculo;

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(5));
	}

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(6));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(15));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(5, 3));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(10, 3));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(5, 3));
	}

	private Sessao sessaoComIngressosSobrando(int quantidade) {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(quantidade * 2);
		sessao.setIngressosReservados(quantidade);

		return sessao;
	}
	
	@Test
	public void DeveInformarPrimeiraSessaoDisponivel(){
		Espetaculo espetaculo = new Espetaculo();
		LocalDate iniciosessao = new LocalDate().withDayOfMonth(9).withMonthOfYear(1).withYear(2016);
		LocalDate finalsessao = new LocalDate().withDayOfMonth(9).withMonthOfYear(1).withYear(2016);
		LocalTime horaespetaculo = new LocalTime().withHourOfDay(17);
		
		
		List<Sessao> sessoes = espetaculo.criaSessoes(iniciosessao, finalsessao, horaespetaculo, Periodicidade.DIARIA);
		
		Assert.assertEquals(9, sessoes.get(0).getInicio().getDayOfMonth());
		Assert.assertEquals(1, sessoes.get(0).getInicio().getMonthOfYear());
		Assert.assertEquals(2016, sessoes.get(0).getInicio().getYear());
		Assert.assertEquals(17, sessoes.get(0).getInicio().getHourOfDay());
	}
	
	@Test
	public void DeveInformarPrimeiraESegundaSessoesDisponiveis(){
		Espetaculo espetaculo = new Espetaculo();
		LocalDate iniciosessao = new LocalDate().withDayOfMonth(9).withMonthOfYear(1).withYear(2016);
		LocalDate finalsessao = new LocalDate().withDayOfMonth(16).withMonthOfYear(1).withYear(2016);
		LocalTime horaespetaculo = new LocalTime().withHourOfDay(17);
		
		
		List<Sessao> sessoes = espetaculo.criaSessoes(iniciosessao, finalsessao, horaespetaculo, Periodicidade.SEMANAL);
		
		Assert.assertEquals(9, sessoes.get(0).getInicio().getDayOfMonth());
		Assert.assertEquals(16, sessoes.get(1).getInicio().getDayOfMonth());
		
	}
	
	
}
